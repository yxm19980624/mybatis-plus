package com.yxm.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Author: yxm
 * @Date: 2020/12/26 17:46
 * @Emial: yxm1136656235@163.com
 * @Description:
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean  第三步    @Qualifier注解指定参数就是Spring管理的方法名  如果方法名太长，可以指定bean name属性后再关联 这里@Qualifier关联的就是指定之后的方法名
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
        *  anon: 无需认证就可以访问
        *  authc: 必须认证才能访问
        *  user: 必须拥有记住我功能才能用
        *  perms: 拥有对某个资源的权限才能访问
        *  role： 拥有某个角色权限才能访问
        * */

        //设置拦截的url以及权限  ！！！！！！！！！！
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/login","anon");
        filterMap.put("/user/insert","authc");
        filterMap.put("/user/delete","authc");
        filterMap.put("/user/update","authc");
        filterMap.put("/user/selectAll","authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
//        filterFactoryBean.setLoginUrl("/user/login");         //这里用失败页面去登录来模仿登录页面请求
        filterFactoryBean.setLoginUrl("/user/fail");
        // 设置登录成功跳转Url
//        filterFactoryBean.setSuccessUrl("/user/index");

        //设置授权 ！！！！！！！！！！！！！ 正常情况下，未授权跳转到未授权页面
        filterMap.put("/user/insert","perms[insert]");
        filterMap.put("/user/selectAll","perms[select]");
        filterMap.put("/user/delete","perms[delete]");
        //设置未授权的请求
        filterFactoryBean.setUnauthorizedUrl("/user/noApply");


        return filterFactoryBean;
    }

    //DefaultWebSecurityManager  第二步     @Qualifier注解指定参数就是Spring管理的方法名  如果方法名太长，可以指定bean name属性后再关联
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(realm);
        return securityManager;
    }

    //创建realm bean对象 自定义类  第一步
    @Bean
    public Realm realm(){
        return new Realm();
    }

}
