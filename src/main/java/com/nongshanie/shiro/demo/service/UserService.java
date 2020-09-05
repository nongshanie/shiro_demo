package com.nongshanie.shiro.demo.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.nongshanie.shiro.demo.model.MyPermission;
import com.nongshanie.shiro.demo.model.Role;
import com.nongshanie.shiro.demo.model.User;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author: zhouxinhang
 * @date: 2020/9/5
 * @Description:
 */
@Service
public class UserService {
    public User getUserById(String username) {
        User user = new User();
        user.setUserName(username);
        user.setPwd("123456");
        Role role = new Role();
        role.setId(username);
        role.setId("roleid");
        MyPermission myPermission = new MyPermission();
        myPermission.setCode("admin");
        role.setPermissions(CollUtil.newArrayList(myPermission));
        user.setRoles(CollUtil.newArrayList(role));
        return user;
    }
}
