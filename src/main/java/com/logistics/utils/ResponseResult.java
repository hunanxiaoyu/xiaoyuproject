package com.logistics.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.logistics.exception.CommonException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T>{
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息,如果有错误时,前端可以获取该字段进行提示
     *
     */
    private String msg;
    /**
     * 查询到的结果
     */
    private T data;
    public ResponseResult(Integer code, String msg, T data){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }
    public static <T> ResponseResult<Void> ok(){
          return ok(null);
    }
    public static <T> ResponseResult<T> ok(T data){
        return new ResponseResult<T>(200,"OK",data);
    }
    public static  <T> ResponseResult<T> error(int code,String msg){
        return new ResponseResult<T>(code,msg,null);
    }
    public static <T> ResponseResult<T> error(CommonException e){
        return new ResponseResult<T>(e.getCode(),e.getMessage(),null);
    }
    public static <T> ResponseResult<T> error(T data){
        return new ResponseResult<T>(500,"ERROR",data);
    }
}

