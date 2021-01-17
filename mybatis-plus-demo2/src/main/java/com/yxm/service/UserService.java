package com.yxm.service;

import com.yxm.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yxm
 * @since 2020-12-24
 */
public interface UserService extends IService<User> {

    List<User> selectAll();

    int insert(User user);

    int delete(User user);

    int updateUser(User user);

    User getUserByName(String name);
}
