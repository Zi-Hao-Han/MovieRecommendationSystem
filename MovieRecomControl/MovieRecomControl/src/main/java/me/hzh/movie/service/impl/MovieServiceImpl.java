package me.hzh.movie.service.impl;

import me.hzh.movie.domain.Movie;
import me.hzh.movie.repository.MovieRepository;
import me.hzh.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Page<Movie> findAll(Integer page,Integer size) {
    	Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
    @Override
    public Movie getOne(Long id) {

        return movieRepository.getOne(id);

    }

	@Override
	public List<Movie> findByMovieName(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByMovieName(searchContent);
	}

	@Override
	public List<Movie> findByDescrip(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByDescrip(searchContent);

	}

	@Override
	public List<Movie> findByTimelong(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByTimelong(searchContent);

	}

	@Override
	public List<Movie> findByShoot(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByShoot(searchContent);
	}

	@Override
	public List<Movie> findByLanguage(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByLanguage(searchContent);
	}

	@Override
	public List<Movie> findByActor(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByActor(searchContent);
	}

	@Override
	public List<Movie> findByDirector(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByDirector(searchContent);
	}

	@Override
	public List<Movie> findByIssue(String searchContent) {
		// TODO Auto-generated method stub
		return movieRepository.findByIssue(searchContent);
	}
    
}
