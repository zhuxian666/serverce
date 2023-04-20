package com.example.demo.javaBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: judy
 * @Description:
 * @Date: Created in 9:14 2019/2/21
 */
@Data
@Slf4j
public class Result<T> {
    private Integer code;
    private T data;
    private String message;
    private Result(T data) {
        this.code = 20000;
        this.data = data;
        this.message= data.toString();
    }

    private Result(CodeMsg codeMsg) {
        this.message=codeMsg.getMsg();
        this.code=codeMsg.getCode();
    }

    //通过构造方法只需要传data就可以
    public static <T>Result<T> success( T data ){
        return new Result<>(data);
    }

    // 这里使用codeMsg 是因为返回结果的是code码不是固定的
    public static <T>Result<T> error(CodeMsg codeMsg){
        if (codeMsg == null) {
            log.warn("codeMsg is null");
            return null;
        }
        return new Result<T>(codeMsg);
    }

}
