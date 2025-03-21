package com.logistics.advice;

import com.logistics.exception.CommonException;
import com.logistics.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
  @ExceptionHandler(CommonException.class)
  public Object handleCommonExceptionAdvice(CommonException e){
      log.error("自定义异常-> {},异常原因: {}",e.getClass().getName(),e.getMessage());
      return processResponse(e);
  }
    private ResponseEntity<ResponseResult<Void>> processResponse(CommonException e){
        return ResponseEntity.status(e.getCode()).body(ResponseResult.error(e));
    }
}
