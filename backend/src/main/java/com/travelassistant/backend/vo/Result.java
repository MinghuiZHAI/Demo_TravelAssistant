package com.travelassistant.backend.vo;

import lombok.Data;

// 定义业务正常与异常方法

@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    /*内容不确定时，定义泛型来实现*/
    private T data;
    private String error;
    private String rawResponse;

    /*//不用lombok时的方法
    public Boolean getSuccess() {
        return success;
    }*/

    /*//不用lombok时的方法
    public void setSuccess(Boolean success) {
        this.success = success;
    }*/

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

//    函数同名，但区别在有无参数：方法的同名
    public static <T> Result<T> ok(T data) {
        Result<T> result = ok();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage("fail");
        return result;
    }

//    业务异常
    public static <T> Result<T> fail(Integer code, String message){
        Result<T> result = fail();
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(String error, String rawResponse){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setError(error);
        result.setRawResponse(rawResponse);
        return result;
    }
}
