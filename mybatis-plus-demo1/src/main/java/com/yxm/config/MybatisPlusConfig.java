package com.yxm.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: yxm
 * @Date: 2020/12/23 16:50
 * @Emial: yxm1136656235@163.com
 * @Description: mybatis-plus插件
 */
@EnableTransactionManagement
@MapperScan("com.yxm.mapper")       //扫描mapper文件夹 让其生效
@Configuration      //配置类
public class MybatisPlusConfig {

    //注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }


    //注册分页插件  直接使用Page对象即可
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return  new PaginationInterceptor();
    }

    //逻辑删除组件
    @Bean
    public ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    //性能分析插件
    @Bean
    @Profile({"test","dev"})    //开发环境和测试环境才生效
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //单位 ms  设置sql执行的最大时间，如果超过则不执行
        performanceInterceptor.setMaxTime(1000);
        //设置sql格式化
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
