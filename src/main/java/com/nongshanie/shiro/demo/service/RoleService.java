package com.nongshanie.shiro.demo.service;

import cn.hutool.core.collection.CollUtil;
import com.nongshanie.shiro.demo.model.MyPermission;
import com.nongshanie.shiro.demo.model.Role;
import org.springframework.stereotype.Service;

/**
 * @author: zhouxinhang
 * @date: 2020/9/5
 * @Description:
 */
@Service
public class RoleService {


    public Role getRoleById(String id) {
        Role role = new Role();
        role.setId(id);
        MyPermission myPermission = new MyPermission();
        myPermission.setCode("admin");
        role.setPermissions(CollUtil.newArrayList(myPermission));
        return role;
    }
}
