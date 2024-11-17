package com.yao.test.exceptionAdvice;

import com.yao.test.controller.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class ExcceptionAdvice {

//    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {

        return Result.error(e.getMessage());
    }
}
