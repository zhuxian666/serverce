package com.example.demo.javaBean;

import lombok.Data;

@Data
public class CodeMsg{
    private Integer code;
    private String msg;
    //使用static的原因是因为直接使用
    public static CodeMsg SUCCESS = new CodeMsg(20000,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"用户名或密码错误");
    public CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
}

