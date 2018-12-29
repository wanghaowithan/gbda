package com.wjwy.gbda.util;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 密码比较过滤器
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    /**
     * 重写了密码比较的方法
     *  第一个参数AuthenticationToken  代表用户在界面上输入的用户名和密码
     *
     *  第二个参数AuthenticationInfo   代表了当前这个用户在数据库中的信息，就会有加密后的密码
     *
     *  返回值：true证明密码比较成功了
     *        false证明密码比较失败，密码输入错误，程序会抛出异常
     *
     */
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //1.将用户在界面输入的密码进行加密
        UsernamePasswordToken upToken = (UsernamePasswordToken) token; //向下转型
        String inputpwd = new String(upToken.getPassword());
        String inputpwdEncrypt =
                Encrypt.md5(inputpwd, upToken.getUsername());//md5hash算法进行加密

        //2.将用户在数据库中的密码读取出来
        String dbPwd = info.getCredentials().toString();
        //3.进行比较
        return super.equals(inputpwdEncrypt, dbPwd);
    }

}
