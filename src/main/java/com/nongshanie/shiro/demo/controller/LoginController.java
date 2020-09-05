package com.nongshanie.shiro.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhouxinhang
 * @date: 2020/9/5
 * @Description:
 */
@RestController
public class LoginController {

/*    //退出的时候是get请求，主要是用于退出
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }*/

    //post登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, @RequestParam("id") String id,@RequestParam("pwd") String pwd) {
        System.out.println("id = " + id);
        System.out.println("pwd = " + pwd);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                id,
                pwd);
        try {
            subject.login(usernamePasswordToken);
            return "home";
        } catch (UnknownAccountException e) {
            //用户名不存在
            model.addAttribute("msg", "用户名不存在");
            System.out.println("\"用户名不存在\" = " + "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            //密码错误
            model.addAttribute("msg", "密码错误");
            System.out.println("\"密码错误\" = " + "密码错误");
            return "login";
        }

    }

    @RequestMapping(value = "/home_page")
    public String index() {
        System.out.println("index() = ======================");
        Subject subject = SecurityUtils.getSubject();
        System.out.println("subject = " + subject);
        Object principal = subject.getPrincipal();
        System.out.println("principal = " + principal);
        boolean zxh = subject.hasRole("roleid");
        System.out.println("roleid = " + zxh);
        boolean zxhddd = subject.hasRole("234444");
        System.out.println("zxhddd = " + zxhddd);

        return "home";
    }
}
