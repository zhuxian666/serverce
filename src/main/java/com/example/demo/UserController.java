package com.example.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.javaBean.Result;
import com.example.demo.javaBean.User;
import com.example.demo.javaBean.User_order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @GetMapping("/user/find")
    public Result<List> query(){
        List<User> list = userMapper.selectList(null);
        return Result.success(list);
    }
    @GetMapping("/user/findorder")
    public Result<List> queryOrderAndUser(){
        List<User_order> list = orderMapper.selectOrderAndUser();
        return Result.success(list);
    }
    @GetMapping("/user/findall")
    public Result<List> queryOrder(){
        List<User> list = userMapper.selectAllUserAndOrder();
        return Result.success(list);
    }

    @GetMapping("/user/findpage")
    public Result<IPage> findByPage(){
        IPage page = userMapper.selectPage(new Page<>(0,5),null);
        return Result.success(page);
    }
    @PostMapping("/user/insert")
    public Result<String> save(User user){
        int i = userMapper.insert(user);
        if (i > 0){
            return Result.success("插入成功");
        }else {
            return Result.success("插入失败");
        }
    }

    @PostMapping("/user/update")
    public Result<String> update(User user){
        int i = userMapper.update(user);
        if (i > 0){
            return Result.success("更新成功");
        }else {
            return Result.success("更新失败");
        }
    }

    @PostMapping("/user/remove")
    public Result<String> remove(int id){
        int i = userMapper.delete(id);
        if (i > 0){
            return Result.success("删除成功");
        }else {
            return Result.success("删除失败");
        }
    }

    @GetMapping("/user/findid")
    public Result<String> queryById(@RequestParam("id") int id){
        User user = userMapper.findById(id);
        if (user == null){
            return Result.success("数据不存在");
        }
        System.out.println(user);
        return Result.success(user.toString());
    }
}
