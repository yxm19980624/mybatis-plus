package com.yxm.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: yxm
 * @Date: 2020/12/23 16:15
 * @Emial: yxm1136656235@163.com
 * @Description: 自动填充时间格式
 */

@Slf4j
@Component  //一定要注册到IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
      log.info("start insert fill ...");
      this.setFieldValByName("createTime",new Date(),metaObject);
      this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update insert fill ...");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
