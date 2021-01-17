package com.yxm.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxm.entity.User;
import com.yxm.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: yxm
 * @Date: 2020/12/28 15:21
 * @Emial: yxm1136656235@163.com
 * @Description:
 */
@Slf4j
public class Realm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("=========Shiro执行了授权=========");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //拿到当前登录的这个用户对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();   //拿到user对象
        log.info("currentUser是:{}" + currentUser);
////        查询数据库 设置权限
        String userRole = currentUser.getRole();
        info.addStringPermission(userRole);
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("=========Shiro执行了认证=========");

        //数据库操作
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userToken.getUsername());
        User user = userMapper.selectOne(queryWrapper);

        if (user != null){
        //可以加密 MD5加密     MD5盐值加密
        //密码认证  shiro自己做了
         return new SimpleAuthenticationInfo(user,user.getPassword(),getName());       //第一个参数名要注入shiro的对象 第二个shiro密码校验 第三个当前realm名
        }
        return null;
    }
}
