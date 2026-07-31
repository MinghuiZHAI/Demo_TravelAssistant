package com.travelassistant.backend.common;
// common包: 放置公共逻辑

import com.travelassistant.backend.vo.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


//将控制器的异常进行处理拦截抛出

/*
*  @RestControllerAdvice
*  是@ControllerAddress与@ResponseBody的组合，
*  作用： 拦截之前定义在TravelController中（@RestController）中所有抛出的异常
*
*  @ExceptionHandler
*  作用：指定异常处理
*  MethodArgumentNotValidException.class
*  专门用来拦截@Valid 中具体的异常
*
*  方法内容为固定写法
*  return Result.fail(400, message) 对外进行暴露
* */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)  // 处理参数校验异常
    public Result<Void> handlerException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return Result.fail(400, message);
    }
}
