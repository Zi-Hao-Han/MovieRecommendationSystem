package me.hzh.movie.vo;

import lombok.Data;

@Data
public class ResultVo<T> {

    //错误码
    private Integer code;

    //错误信息
    private String msg;

    //返回数据
    private T Data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return Data;
	}

	public void setData(T data) {
		Data = data;
	}
    


}