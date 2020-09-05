package com.nongshanie.shiro.demo.model;

import lombok.Data;

import java.util.List;

/**
 * @author: zhouxinhang
 * @date: 2020/9/5
 * @Description:
 */
@Data
public class Role {
    private String id;

    private List<MyPermission> permissions;
}
