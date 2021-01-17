package com.yxm;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @Author: yxm
 * @Date: 2020/12/23 18:56
 * @Emial: yxm1136656235@163.com
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //构建一个代码生成器对象
        AutoGenerator autoGenerator = new AutoGenerator();

        /** 配置策略*/

        // 1. 全局配置 com.baomidou.mybatisplus.generator.config.GlobalConfig;
        GlobalConfig globalConfig = new GlobalConfig();
        String path = System.getProperty("user.dir");   //获取当前项目根路径
        globalConfig.setOutputDir(path + "/mybatis-plus-demo1/src/main/java");    //设置输出目录
        globalConfig.setAuthor("yxm");      //设置作者
        globalConfig.setOpen(false);        //设置是否打开资源管理器
        globalConfig.setFileOverride(true);    //是否覆盖原来生成的
        globalConfig.setServiceName("%sService");   //设置Service I前缀
        globalConfig.setServiceImplName("%sServiceImpl");   //设置ServiceImpl I前缀
        globalConfig.setIdType(IdType.ID_WORKER);       //设置主键生成策略  默认为雪花算法 也可以设置为自增长
        globalConfig.setDateType(DateType.ONLY_DATE);   //设置日期时间 仅仅只为时间
//        globalConfig.setSwagger2(true);             //设置swagger文档
        //放到代码生成器对象里面
        autoGenerator.setGlobalConfig(globalConfig);


        //2.设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);       //设置数据库类型为MySQL
        //放到代码生成器对象里面
        autoGenerator.setDataSource(dataSourceConfig);

        //3.设置包
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("yxm");     //设置模块名
        packageConfig.setParent("com.yxm");       //设置放在哪个包下
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        //放到代码生成器对象里面
        autoGenerator.setPackageInfo(packageConfig);

        //4.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("user");      //设置要映射的表名，可以同时设置多个表 strategyConfig.setInclude("user","...");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);    //设置表名下划线转驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);  //设置字段名下划线转驼峰命名
        strategyConfig.setEntityLombokModel(true);  //是否使用Lombok
        strategyConfig.setRestControllerStyle(true);    //是否使用restful风格的controller
        strategyConfig.setLogicDeleteFieldName("deleted");      //设置逻辑删除字段

        //设置逻辑删除字段的时候  需要设置自动填充配置(时间格式的自动填充)
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategyConfig.setTableFillList(tableFills);

        //乐观锁配置
        strategyConfig.setVersionFieldName("version");      //设置乐观锁字段
        strategyConfig.setControllerMappingHyphenStyle(false);//设置请求路径为下划线 true则localhost:8080/hello_index_1这种带下划线格式
        autoGenerator.setStrategy(strategyConfig);

        //执行
        autoGenerator.execute();
    }
}
