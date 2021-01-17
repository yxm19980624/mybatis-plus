package com.yxm.controller;

import com.yxm.common.CommonResult;
import com.yxm.entity.User;
import com.yxm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxm
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户操作接口")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "增加用户",notes = "增加用户")
    @PostMapping("/insert")
    public CommonResult<User> insert(@RequestBody User user){
        int result = userService.insert(user);
       if (result > 0 ){
          return CommonResult.success(user);
       }
       return CommonResult.failed(user);
    }

    @ApiOperation(value = "删除用户",notes = "删除用户")
    @PostMapping("/delete")
    public CommonResult<User> delete(@RequestBody User user){
        int result = userService.delete(user);
        if (result > 0 ){
            return CommonResult.success(user);
        }
        return CommonResult.failed(user,"未知原因");
    }

    @ApiOperation(value = "修改用户",notes = "修改用户")
    @PostMapping("/update")
    public CommonResult<User> update(@RequestBody User user){
        User user1 = userService.getById(user.getId());     //为了乐观锁实现
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setAge(user.getAge());
        int result = userService.updateUser(user1);
        if (result > 0 ){
            return CommonResult.success(user1);
        }
        return CommonResult.failed(user1);
    }

    @ApiOperation(value = "查询所有用户",notes = "查询所有用户")
    @PostMapping("/selectAll")
    public CommonResult<List<User>> selectAll(){
        return CommonResult.success(userService.selectAll());
    }

    @ApiOperation(value = "模拟主页",notes = "模拟主页")
    @GetMapping("/index")
    public CommonResult<User> index(){
        return CommonResult.success(null,"您已成功登录，这就是首页！");
    }

    @ApiOperation(value = "模拟失败",notes = "模拟失败")
    @GetMapping("/fail")
    public CommonResult<User> fail(){
        return CommonResult.success(null,"请您先登录");
    }

    @ApiOperation(value = "模拟未授权",notes = "模拟未授权")
    @GetMapping("/noApply")
    public CommonResult<User> noApply(){
        return CommonResult.success(null,"您没有权限");
    }

    @ApiOperation(value = "登录",notes = "登录")
    @PostMapping("/login")
    public CommonResult<User> login(@RequestBody User user){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装成token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            subject.login(token);
            return CommonResult.success(userService.getUserByName(user.getName()),"登录成功");
        } catch (UnknownAccountException e) {
            return CommonResult.failed(null,"无此用户");
        }catch (IncorrectCredentialsException e){
            return CommonResult.failed(null,"密码错误");
        }
    }
}

