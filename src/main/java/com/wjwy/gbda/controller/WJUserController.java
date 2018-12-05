package com.wjwy.gbda.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjwy.gbda.entity.WJUser;
import com.wjwy.gbda.service.WJUserService;
import com.wjwy.gbda.util.ReturnResult;
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

@RequestMapping(value = "/user")
@Controller
@EnableTransactionManagement
public class WJUserController {
    private static Logger logger = LoggerFactory.getLogger(WJUserController.class);
    @Getter
    @Setter
    @Resource
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

    /**
     * ajax登录请求借口
     *
     * @param wjUser 用户信息
     * @return ajax返回值
     */
    @PostMapping("ajaxLogin")
    @ResponseBody
    public ReturnResult adminLogin(WJUser wjUser) {
        ReturnResult result = new ReturnResult();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(wjUser.getUserName(),
                        wjUser.getPassword());
        try {
            subject.login(token);
            result.setToken(subject.getSession().getId());
            result.setMsg("登录成功");
            result.setCode(200);
            result.setResult(subject.getPrincipal());//已登录用户信息
        } catch (IncorrectCredentialsException e) {
            result.setMsg("密码错误");
            result.setCode(400);
        } catch (LockedAccountException e) {
            result.setMsg("登录失败，该用户已被冻结");
            result.setCode(400);
        } catch (AuthenticationException e) {
            result.setMsg("该用户不存在");
            result.setCode(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return 状态码信息
     */
    @RequestMapping(value = "/unLogin")
    public ReturnResult unLogin() {
        ReturnResult result = new ReturnResult();
        result.setMsg("未登录");
        result.setCode(400);
        return result;
    }

    //登陆验证，这里方便url测试，正式上线需要使用POST方式提交
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
                return "redirect:/user/indexLogin";
            } catch (IncorrectCredentialsException ice) {
                logger.info("对用户[" + wjUser.getUserName()
                            + "]进行登录验证..验证失败-password didn't match");
                token.clear();
                System.out.println("用户[" + wjUser.getUserName() + "]登录认证失败,"
                                   + "重新登陆");
                return "redirect:/user/indexLogin";
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

    //错误页面展示
    @GetMapping("/403")
    public String error() {
        return "error";
    }
}
