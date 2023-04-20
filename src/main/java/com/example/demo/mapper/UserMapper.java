package com.example.demo.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.javaBean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "username",property = "username"),
            @Result(column = "age",property = "age"),
            @Result(column = "gander",property = "gander"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "address",property = "address"),
            @Result(column = "id",property = "order",javaType = List.class,
                many = @Many(select = "com.example.demo.mapper.OrderMapper.selectByUid")
            )
    })
    public List<User> selectAllUserAndOrder();

    @Select("select * from user")
    public List<User> find();
    @Insert("insert into user values (#{id},#{name},#{username},#{age},#{gander},#{phone},#{address})")
    public int insert(User user);

    @Delete("delete from user where id=#{id}")
    public int delete(int id);

    @Select("select * from user where id=#{id}")
    public User findById(int id);

    @Update("update user set name=#{name},username=#{username},age=#{age},gander=#{gander},phone=#{phone},address=#{address} where id=#{id}")
    public int update(User user);
}


