package com.wjwy.gbda.shiro;

import com.wjwy.gbda.entity.WJRole;
import com.wjwy.gbda.entity.WJRolePower;
import com.wjwy.gbda.entity.WJUser;
import com.wjwy.gbda.service.WJRoleService;
import com.wjwy.gbda.service.WJUserService;
import com.wjwy.gbda.util.Encrypt;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    @Getter
    @Setter
    private WJUserService wjuserService;
    @Autowired
    @Getter
    @Setter
    private WJRoleService wjRoleService;

    /**
     * 授权认证
     * 验证用户是否具有某某权限
     * 当页面上碰到Shiro标签时就会调用这个方法，当第一次碰到时才调用这个方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("权限认证开始.......");
        //声明AuthorizationInfo的一个子类对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //1.得到用户信息
        WJUser user = (WJUser) pc.fromRealm(this.getName()).iterator().next();
        //2.通过对象导航得到用户的角色
        WJRole roles = wjRoleService.selectByPrimaryKey(user.getRoleId());
        if (roles != null) {
            List<String> permissions = new ArrayList<>();
            //得到角色，并通过对象导航，进一步加载这个角色下的模块
            List<WJRolePower> wjRolePowers = roles.getWjRolePowerList();
            //遍历权限的集合，得到每个模块的信息
            for (WJRolePower rolePower : wjRolePowers) {
                permissions.add(rolePower.getWjPower().getPowerUrl());
            }
            info.addStringPermissions(permissions);//权限集合
            info.addRole(roles.getRoleName());//角色
        }
        return info;
    }

    /**
     * 登录认证（在登录时就会调用这个方法）    Subject.login();
     * 参数：AuthenticationToken代表用户在界面上输入的用户名和密码
     * 返回值不为null就会执行密码比较器
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.将token转化为子类对象
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        logger.info("验证当前Subject时获取到token为：" + upToken.toString());
        String username = upToken.getUsername();
        //2.从token中获取用户界面输入的用户名
        //3.调用业务逻辑层，根据用户名查询用户对象
        WJUser userList = wjuserService.selectForLogin(username);
        //账号不存在
        if (userList == null) {
            logger.info(username + "没找到帐号");
            throw new UnknownAccountException();// 没找到帐号
        }
        // 账号未启用
        if (userList.getStatus() == 0) {
            logger.info(username + "账号已失效");
            throw new LockedAccountException();
        }
        //principal代表用户信息  credentials代表用户的密码         第三个参数：只要是一个字符串就可以
        return new SimpleAuthenticationInfo(userList,
                Encrypt.md5(userList.getPassword(), userList.getUserName()),
                this.getName());
    }
}
