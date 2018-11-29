package com.wjwy.gbda.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjwy.gbda.entity.WJUser;
import com.wjwy.gbda.service.WJUserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping(value = "/user")
@Controller
@EnableTransactionManagement
public class WJUserController {
    private static Logger logger = LoggerFactory.getLogger(WJUserController.class);
    @Resource
    @Getter
    @Setter
    private WJUserService wjUserService;

    @ResponseBody
    @RequestMapping(value = "/One")
    @RequiresPermissions("user:list")
    public JSONObject findOne() {
        WJUser wjUser = wjUserService.selectByPrimaryKey(1);
        JSONObject json = new JSONObject();
        json.put("data", wjUser);
        return json;
    }

    //@RequiresPermissions("user/list")//shiro权限控制注解
    //登录成功后，跳转的页面
    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    //未登录，可以访问的页面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/indexLogin")
    public String test() {
        return "login";
    }

    //登陆验证，这里方便url测试，正式上线需要使用POST方式提交
    @RequestMapping(value = "/login")
    public String login(WJUser wjUser) {
        if (wjUser.getUserName() != null && wjUser.getPassword() != null) {
            UsernamePasswordToken token =
                    new UsernamePasswordToken(wjUser.getUserName(), wjUser.getPassword(),
                            "login");
            token.setRememberMe(true);//rememberMe设置
            Subject currentUser = SecurityUtils.getSubject();
            logger.info("对用户[" + wjUser.getUserName() + "]进行登录验证..验证开始");
            try {
                currentUser.login(token);
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    logger.info("用户[" + wjUser.getUserName() + "]登录认证通过"
                                + "(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    return "redirect:/user/success";
                }
            } catch (UnknownAccountException uae) {
                logger.info("对用户[" + wjUser.getUserName()
                            + "]进行登录验证..验证失败-username wasn't in the system");
                token.clear();
                System.out.println("用户[" + wjUser.getUserName() + "]登录认证失败,"
                                   + "重新登陆");
                return "redirect:/user/test";
            } catch (IncorrectCredentialsException ice) {
                logger.info("对用户[" + wjUser.getUserName()
                            + "]进行登录验证..验证失败-password didn't match");
                token.clear();
                System.out.println("用户[" + wjUser.getUserName() + "]登录认证失败,"
                                   + "重新登陆");
                return "redirect:/user/test";
            } catch (LockedAccountException lae) {
                logger.info("对用户[" + wjUser.getUserName()
                            + "]进行登录验证..验证失败-account is locked in the system");
                token.clear();
                System.out.println("用户[" + wjUser.getUserName() + "]登录认证失败,"
                                   + "重新登陆");
                return "redirect:/user/indexLogin";
            } catch (AuthenticationException ae) {
                logger.error(ae.getMessage());
            }
        }
        return null;
    }

    /**
     * ajax登录请求接口方式登陆
     *
     * @param userName 用户名
     * @param password 密码
     * @return 返回登录信息
     */
    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public Map<String, Object> submitLogin(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    //登出
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }

    //错误页面展示
    @GetMapping("/403")
    public String error() {
        return "error";
    }
}
