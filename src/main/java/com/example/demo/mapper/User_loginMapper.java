package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.javaBean.UserList;
import com.example.demo.javaBean.User_login;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface User_loginMapper extends BaseMapper<User_login> {
    @Select("select * from user_login where username=#{username}")
    public User_login loginfind(String username);
    @Select("select * from user_login where phone=#{phone}")
    public List<UserList> loginfindphone(String phone);
    @Select("select * from user_login where username=#{username}")
    public List<UserList> loginfindusername(String username);
    @Select("select * from user_login where username=#{username} AND phone=#{phone}")
    public List<UserList> loginfindByUserAndPho(String username,String phone);
    @Select("select username from user_login where id=#{id}")
    public String findById(int id);
    @Select("select id,username,phone,status,email from user_login")
    public List<UserList> list();

    @Insert("insert into user_login values (#{id},#{username},#{password},#{phone},#{status},#{email})")
    public int insertUser(User_login user);
    @Update("update user_login set id=#{id},username=#{username},password=#{password},phone=#{phone},status=#{status},email=#{email} where id=#{id}")
    public int update(User_login user);
    @Delete("delete from user_login where id=#{id}")
    public int delete(int id);
    @Select("select * from user_login where id=#{id}")
    public List<User_login> listById(int id);
}
