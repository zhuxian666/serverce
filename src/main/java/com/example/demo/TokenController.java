package com.example.demo;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.JwtConfig;
import com.example.demo.javaBean.Result;
import com.example.demo.javaBean.UserList;
import com.example.demo.javaBean.User_login;
import com.example.demo.mapper.User_loginMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.example.demo.javaBean.CodeMsg.SERVER_ERROR;
import static com.example.demo.javaBean.CodeMsg.SUCCESS;

@RestController
@CrossOrigin
public class TokenController {

    @Resource
    private JwtConfig jwtConfig ;
    @Autowired
    private User_loginMapper userLoginMapper;
    /**
     * 用户登录接口
     * @param
     * @param
     * @return
     */
    @PostMapping("/user/login")
    public Result<?> login (@RequestBody Map<String, String> data
//            @RequestParam("userName") String userName,
//                            @RequestParam("passWord") String passWord
    ){
        JSONObject json = new JSONObject();
        /** 验证userName，passWord和数据库中是否一致，如不一致，直接return ResultTool.errer(); 【这里省略该步骤】*/
        User_login user = userLoginMapper.loginfind(data.get("username"));
        if (user.getUsername().equals(data.get("username")) && user.getPassword().equals(data.get("password"))){
            String token = jwtConfig.createToken(user.getId().toString()) ;
            if (!StringUtils.isEmpty(token)) {
                json.put("token",token) ;
            }
            return Result.success(json) ;
        }else {
            return Result.error(SERVER_ERROR);
        }

//        System.out.println(user.getUsername());
//        String userId = 5 + "";
//        String token = jwtConfig.createToken(userId) ;
//        if (!StringUtils.isEmpty(token)) {
//            json.put("token",token) ;
//        }
//        return Result.success(json) ;

        // 这里模拟通过用户名和密码，从数据库查询userId
        // 这里把userId转为String类型，实际开发中如果subject需要存userId，则可以JwtConfig的createToken方法的参数设置为Long类型

    }

    /**
     * 需要 Token 验证的接口
     */
    @GetMapping ("/user/info")
    public Result<String> info (HttpServletRequest request){
        String token = request.getHeader("token");
        Claims user = JwtConfig.getTokenClaim(token);
        int id = Integer.parseInt(user.get("sub", String.class));
        String username = userLoginMapper.findById(id);
        return Result.success(username) ;
    }
    /**
     * 根据请求头的token获取userId
     * @param request
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<?> getUserInfo(HttpServletRequest request){
        String usernameFromToken = jwtConfig.getUsernameFromToken(request.getHeader("token"));
        return Result.success(usernameFromToken) ;
    }

    /*
        为什么项目重启后，带着之前的token还可以访问到需要info等需要token验证的接口？
        答案：只要不过期，会一直存在，类似于redis
     */
    @PostMapping("/user/logout")
    public Result logout(){
        return Result.success(SUCCESS);
    }

    @GetMapping("/user/list")
    public Result getUserList(int pageNo, int pageSize, String username, String phone){
        if(!"".equals(username) & !"".equals(phone)){
            List<UserList> list = userLoginMapper.loginfindByUserAndPho(username,phone);
            return Result.success(list) ;
        }else if (!"".equals(phone)){
            List<UserList> list = userLoginMapper.loginfindphone(phone);
            return Result.success(list) ;
        }else if (!"".equals(username)){
            List<UserList> list = userLoginMapper.loginfindusername(username);
            return Result.success(list) ;
        }else {
            IPage page = userLoginMapper.selectPage(new Page<>(pageNo,pageSize),null);
            List<UserList> list = userLoginMapper.list();
            return Result.success(page) ;
        }
    }
    @PostMapping("/user/listinsert")
    public Result insert(@RequestBody User_login user){
        System.out.println(user);
        if (userLoginMapper.insertUser(user) > 0){
            return Result.success("添加成功");
        }else {
            return Result.success("添加失败");
        }
    }
    @PutMapping("/user/listupdate")
    public Result update(@RequestBody User_login user){
        if (userLoginMapper.update(user) > 0){
            return Result.success("修改成功");
        }else {
            return Result.success("修改失败");
        }
    }
    @DeleteMapping ("/user/listdelete")
    public Result delete(@RequestParam int id){
        if (userLoginMapper.delete(id) > 0){
            return Result.success("删除成功");
        }else {
            return Result.success("删除失败");
        }
    }
    @GetMapping ("/user/listbyid/{userId}")
    public Result listById(@PathVariable(value = "userId") int userId){
        List<User_login> user = userLoginMapper.listById(userId);
        return Result.success(user);
    }
}