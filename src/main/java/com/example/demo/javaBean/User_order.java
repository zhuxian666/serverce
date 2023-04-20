package com.example.demo.javaBean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


@Data
public class User_order {
    private Integer id;
    private String total;
    private Integer uid;
    @TableField(exist = false)
    private User user;

}
