package com.example.demo.javaBean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_login {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;
    private String phone;
    private String status;
    private String email;

}
