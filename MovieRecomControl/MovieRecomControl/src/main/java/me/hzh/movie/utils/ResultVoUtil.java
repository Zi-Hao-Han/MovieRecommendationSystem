package me.hzh.movie.utils;

import me.hzh.movie.enums.ResultVoCodeEnum;
import me.hzh.movie.vo.ResultVo;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVoCodeEnum.SUCCESS.getCode());
        resultVo.setMsg(ResultVoCodeEnum.SUCCESS.getMsg());
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success(){
        return success(null);
    }


    public static ResultVo error(Integer code,String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}