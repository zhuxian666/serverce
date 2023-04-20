package com.example.demo.mapper;

import com.example.demo.javaBean.User;
import com.example.demo.javaBean.User_order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from user_order where uid=#{uid}")
    public List<User_order> selectByUid(int uid);

    @Select("select id from user_order where uid=#{uid}")
    public int selectId(int uid);
    @Select("select * from user_order")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "total",property = "total"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "uid",property = "user",javaType = User.class,
                    one = @One(select = "com.example.demo.mapper.UserMapper.findById")
            )
    })
    public List<User_order> selectOrderAndUser();
}
