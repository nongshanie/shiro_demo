package com.nongshanie.shiro.demo.shiro;

import com.nongshanie.shiro.demo.service.RoleService;
import com.nongshanie.shiro.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nongshanie.shiro.demo.model.*;

import java.util.Set;

/**
 * @author: zhouxinhang
 * @date: 2020/9/5
 * @Description:
 */
@Component

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("principals ======================= " + principals);
        String userName = principals.getPrimaryPrincipal().toString();
        //User user  = (User)principals.getPrimaryPrincipal();
        User user = userService.getUserById(userName);
        System.out.println("user = " + user);
        System.out.println("User:"+user.toString()+" roles count:"+user.getRoles().size());

        for(Role role:user.getRoles()){
            authorizationInfo.addRole(role.getId());
            role=roleService.getRoleById(role.getId());
            System.out.println("Role:"+role.toString());
            for(MyPermission p:role.getPermissions()){
                System.out.println("Permission:"+p.toString());
                authorizationInfo.addStringPermission(p.getCode());
            }
        }
        System.out.println("权限配置-->authorizationInfo"+authorizationInfo.toString());
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println("getPrincipal = " + username);
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.getUserById(username);
        System.out.println("----->>userInfo="+user);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(), //用户名
                user.getPwd(), //密码
                getName()  //realm name
        );
        System.out.println("authenticationInfo = " + authenticationInfo);
        return authenticationInfo;
    }
}
