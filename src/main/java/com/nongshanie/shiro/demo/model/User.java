package com.nongshanie.shiro.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

/**
 * @author: zhouxinhang
 * @date: 2020/9/5
 * @Description:
 */
@Data
@Getter
@Setter
public class User {
    private List<Role> roles;

    private String userName;

    private String pwd;
}
