package me.hzh.movie.web;


import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import io.jsonwebtoken.Claims;
import me.hzh.movie.domain.Audience;
import me.hzh.movie.domain.CheckMovie;
import me.hzh.movie.domain.Movie;
import me.hzh.movie.domain.Rate;
import me.hzh.movie.domain.RecommendedMovie;
import me.hzh.movie.domain.Search;
import me.hzh.movie.domain.Type;
import me.hzh.movie.service.MovieService;
import me.hzh.movie.service.impl.CheckServiceImpl;
import me.hzh.movie.service.impl.RateServiceImpl;
import me.hzh.movie.service.impl.RecommendServiceImpl;
import me.hzh.movie.service.impl.SearchServiceImpl;
import me.hzh.movie.service.impl.TypeServiceImpl;
import me.hzh.movie.utils.JwtHelper;
import me.hzh.movie.utils.JythonAPI;
import me.hzh.movie.utils.ResultVoUtil;
import me.hzh.movie.vo.ResultVo;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class MovieController {
    private MovieService movieService;
    @Value("${qcloud.cos.secretId}")
    private String secretId;
    @Value("${qcloud.cos.secretKey}")
    private String secretKey;
    @Value("${qcloud.cos.region}")
    private String region;
    @Value("${qcloud.cos.bucketName}")
    private String bucketName;
    @Value("${cos.server}")
    private Boolean cosServer;
    @Value("${tx.server}")
    private Boolean txServer;
    @Value("${cloudinary.server}")
    private Boolean cloudServer;
    @Value("${img.address}")
    private String imgAddress;
    @Value("${cos.address}")
    private String cosAddress;

    @Value("${recommend.size}")
    private Integer recommendSize;
    private RateServiceImpl rateService;
    private RecommendServiceImpl recommendService;
    private FastFileStorageClient fastFileStorageClient;
    private TypeServiceImpl typeService;
    private SearchServiceImpl searchService;
    private CheckServiceImpl checkService;

    @Autowired
    public MovieController(MovieService movieService, RecommendServiceImpl recommendService,RateServiceImpl rateService,TypeServiceImpl  typeService,SearchServiceImpl searchService,CheckServiceImpl checkService,FastFileStorageClient fastFileStorageClient) {
        this.movieService = movieService;
        this.recommendService = recommendService;
        this.typeService = typeService;
        this.searchService = searchService;
        this.fastFileStorageClient = fastFileStorageClient;
        this.rateService = rateService;
        this.checkService = checkService;
    }

    @RequestMapping("/api/jwt/movie/upload.do")
    public ResultVo upload(MultipartFile file,String movieName) throws IOException {
        if(file.isEmpty()){
            return ResultVoUtil.error(400,"error");
        }
        String imgName = "";
        if(txServer){
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            System.out.println(storePath.getFullPath());
            imgName = imgAddress+storePath.getFullPath();
        }


        if(cloudServer){

            String suffix ="."+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            File tempFile = File.createTempFile("tem",suffix);
            file.transferTo(tempFile);
            Cloudinary cloudinary = Singleton.getCloudinary();
            Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());
            imgName = (String) uploadResult.get("secure_url");

        }
        if(cosServer){
            long millis = System.currentTimeMillis();
            //加上三位随机数
            Random random = new Random();
            int end3 = random.nextInt(999);
            //如果不足三位前面补0
            String str = millis + String.format("%03d", end3);
            String suffix ="."+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            imgName = cosAddress+"/"+str+suffix;
            // 1 初始化用户身份信息(secretId, secretKey)
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            ClientConfig clientConfig = new ClientConfig(new Region(region));
            // 3 生成cos客户端
            COSClient cosclient = new COSClient(cred, clientConfig);

            String key = "/"+str+suffix;
            File tempFile = File.createTempFile(str, suffix);
            file.transferTo(tempFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, tempFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            cosclient.shutdown();
        }
        Movie movie = new Movie();
        movie.setMovieName(movieName);
        movie.setImgName(imgName);
        movieService.save(movie);
        return ResultVoUtil.success(imgName);

    }

    @RequestMapping("/api/movie/list.do")
    public ResultVo list(){
        List<Movie> movieList = movieService.findAll();
        return ResultVoUtil.success(movieList);
    }
    @RequestMapping("/api/movie/listPage.do")
    public ResultVo list(Integer page,Integer size){
        Page<Movie> movieList = movieService.findAll(page,size);
        return ResultVoUtil.success(movieList);
    }
    @RequestMapping("/api/movie/info.do")
    public ResultVo list(Long id){
        Movie movie  = movieService.getOne(id);
        return ResultVoUtil.success(movie);
    }
    @RequestMapping("/api/jwt/movie/recommend.do")
    public ResultVo recommend(HttpServletRequest request) throws TasteException {
        Claims claims = (Claims) request.getAttribute("claims");
        
//        推荐电影获取走matout技术
        List<RecommendedItem> recommendedItems = new ArrayList<>();       
        List<Rate> rateList = rateService.findByUserId(Long.parseLong(String.valueOf(claims.get("userid"))));
        if(rateList.size() != 0){
            recommendedItems = recommendService.recommenderItem(Long.parseLong(String.valueOf(claims.get("userid"))), recommendSize);
        }
        return ResultVoUtil.success(recommendedItems);

        
//      推荐电影获取走python代码        
//        List<RecommendedMovie> recommendedItems = new ArrayList<>();
//        List<Search> searchList = searchService.findByUserId(Long.parseLong(String.valueOf(claims.get("userid"))));
//    	String searchString = "";
//    	Long id = (long) 0;
//    	for(int i=0;i<searchList.size();i++){
//    		if(searchList.get(i).getId()>id){
//    			searchString = "#searchcontent:"+searchList.get(i).getContent()+"#searchresult:"+searchList.get(i).getMovieId();
//    			id = searchList.get(i).getId();
//    		} 
//    	}
//        List<CheckMovie> checkmovieList = checkService.findByUserId(Long.parseLong(String.valueOf(claims.get("userid"))));
//    	String checkMovieString = "#favorite:";
//        for(int i=0;i<checkmovieList.size();i++){
//        	checkMovieString += checkmovieList.get(i).getMovieId();
//        	checkMovieString += "-";
//        	checkMovieString += checkmovieList.get(i).getNumber();
//        	if(i<(checkmovieList.size()-1))
//        	checkMovieString += ",";
//    	}
//        List<Rate> rateList = rateService.findByUserId(Long.parseLong(String.valueOf(claims.get("userid"))));
//        String ratetring = "#rating:";
//        for(int i=0;i<rateList.size();i++){
//        	ratetring += rateList.get(i).getMovieId();
//        	ratetring += "-";
//        	ratetring += rateList.get(i).getRate();
//        	if(i<(rateList.size()-1))
//        		ratetring += ",";
//    	}
//        String sourcedata = "userid:"+claims.get("userid")+searchString+checkMovieString+ratetring;
//        String recmovieResultPython = "";
//        String recmovieString = null;
//        JythonAPI papi = new JythonAPI();
//        papi.passPara2Python(sourcedata);
//        try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        recmovieResultPython = papi.getResultFromPython();
//        if(!recmovieResultPython.equals("")){
//        	recmovieString = recmovieResultPython.split(":")[1];
//        	String recmovieList [] = recmovieString.split(",");
//        	for(String recmovie:recmovieList){
//            	RecommendedMovie rec = new RecommendedMovie();
//            	rec.setItemID(Long.valueOf(recmovie));
//            	recommendedItems.add(rec);
//        	}
//        }
//        return ResultVoUtil.success(recommendedItems);
    }
    @RequestMapping("/api/jwt/movie/searchresult.do")
    public ResultVo searchresult(HttpServletRequest request){
//    	List<Movie> movieList = new ArrayList<Movie>();
    	List<Long> movieidList = new ArrayList<Long>();
    	Claims claims = (Claims) request.getAttribute("claims");
    	List<Search> searchList = searchService.findByUserId(Long.parseLong(String.valueOf(claims.get("userid"))));
    	String movieString = "";
    	Long id = (long) 0;
    	for(int i=0;i<searchList.size();i++){
    		if(searchList.get(i).getId()>id){
    			movieString = searchList.get(i).getMovieId();
    			id = searchList.get(i).getId();
    		} 
    	}
    	if(movieString.trim().length()>0){
    		String [] movies = movieString.split(",");
    		for(int i=0;i<movies.length;i++){
        		if(movies[i]!=null){
        			movieidList.add(Long.valueOf(movies[i]));
//        			Movie movie = movieService.getOne(Long.valueOf(movies[i]));
//        			movieList.add(movie);
        		}
    		}
    	}
//    	return ResultVoUtil.success(movieList);
    	return ResultVoUtil.success(movieidList);
    }
    @RequestMapping("/api/jwt/movie/search.do")
    public ResultVo search(HttpServletRequest request){
    	Claims claims = (Claims) request.getAttribute("claims");
    	String searchtype = request.getParameter("searchtype");
    	String searchcontent = request.getParameter("searchcontent");
    	Long userid = Long.parseLong(String.valueOf(claims.get("userid")));
    	List<Movie> movieList = null;
    	List<Type> movieidList = null;
    	switch(searchtype)
    	{
	    	case "movieName":
	    		movieList = movieService.findByMovieName(searchcontent);
	    		break;
	    		
	    	case "descrip":
	    		movieList = movieService.findByDescrip(searchcontent);
	    		break;
	    		
	    	case "timelong":
	    		movieList = movieService.findByTimelong(searchcontent);
	    		break;
	    		
	    	case "shoot":
	    		movieList = movieService.findByShoot(searchcontent);
	    		break;
	    		
	    	case "language":
	    		movieList = movieService.findByLanguage(searchcontent);
	    		break;
	    		
	    	case "actors":
	    		movieList = movieService.findByActor(searchcontent);
	    		break;
	    		
	    	case "director":
	    		movieList = movieService.findByDirector(searchcontent);
	    		break;
	    		
	    	case "issue":
	    		movieList = movieService.findByIssue(searchcontent);
	    		break;
	    	case "type":
	    		movieidList= typeService.findByfilmtype(searchcontent);
	    		break;
	    	default:
	    		break;
    	}
    	Search search = new Search();
    	search.setContent(searchtype+":"+searchcontent);
    	search.setUserId(userid);
    	String movies = "";
    	if(movieList != null){
    		for(int i = 0;i<movieList.size();i++ ){
    			if(i<10){
	    			movies+=movieList.get(i).getId();
	        		movies+=",";
    			}
        	}
    	}
    	else{
    		for(int i = 0;i<movieidList.size();i++ ){
    			if(i<10){
            		movies+=movieidList.get(i).getMovieid();
            		movies+=",";
    			}
        	}
    	}
    	search.setMovieId(movies);
    	searchService.save(search);
        return ResultVoUtil.success(movieList);
    }
    @RequestMapping("/api/movie/setinfo.do")
    public ResultVo setinfo(Long movieId,HttpServletRequest request){
    	String authHeader = request.getHeader("authorization");
        if (authHeader != null && authHeader.startsWith("bearer;")) {
        	String token = authHeader.substring(7);
        	BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        	Audience audience = (Audience) factory.getBean("audience");
        	Claims claims = JwtHelper.parseJWT(token,audience.getBase64Secret());
        	Long userid = Long.parseLong(String.valueOf(claims.get("userid")));
        	CheckMovie check = checkService.findByMovieIdAndUserId(movieId, userid);
        	if(check == null){
        		check = new CheckMovie();
            	check.setMovieId(movieId);
            	check.setUserId(userid);
            	check.setNumber((long) 1);
        	}
        	else
        	{
        		check.setNumber(check.getNumber()+(long)1);
        		
        	}
        	checkService.save(check);
//        	checkService.update(check);
        }
    	
    	return null;
    }
}
