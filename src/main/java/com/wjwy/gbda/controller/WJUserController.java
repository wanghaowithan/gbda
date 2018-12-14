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
    @Getter
    @Setter
    @Resource
    private ReturnResult returnResult;

    @ResponseBody
    @GetMapping(value = "/One")
    @RequiresPermissions("user:list")
    public JSONObject findOne() {
        WJUser wjUser = wjUserService.selectByPrimaryKey(1);
        JSONObject json = new JSONObject();
        json.put("data", wjUser);
        return json;
    }

    //@RequiresPermissions("user/list")//shiro权限控制注解
    //登录成功后，跳转的页面
    @GetMapping("/success")
    public String success() {
        return "success";
    }

    //未登录，可以访问的页面
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/indexLogin")
    public String test() {
        return "login";
    }

    /**
     * ajax登录请求借口
     *
     * @param wjUser 用户信息
     * @return ajax返回值
     */
    @GetMapping("ajaxLogin")
    @ResponseBody
    public ReturnResult adminLogin(WJUser wjUser) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token =
                new UsernamePasswordToken(wjUser.getUserName(),
                        wjUser.getPassword());
        try {
            subject.login(token);
            returnResult.setToken(subject.getSession().getId());
            returnResult.setMsg("登录成功");
            returnResult.setCode(200);
            returnResult.setResult(subject.getPrincipal());//已登录用户信息
        } catch (IncorrectCredentialsException e) {
            returnResult.setMsg("密码错误");
            returnResult.setCode(400);
        } catch (LockedAccountException e) {
            returnResult.setMsg("登录失败，该用户已被冻结");
            returnResult.setCode(400);
        } catch (AuthenticationException e) {
            returnResult.setMsg("该用户不存在");
            returnResult.setCode(400);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnResult;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return 状态码信息
     */
    @GetMapping(value = "/unLogin")
    public ReturnResult unLogin() {
        returnResult.setMsg("未登录");
        returnResult.setCode(400);
        return returnResult;
    }

    //登陆验证，这里方便url测试，正式上线需要使用POST方式提交
    @GetMapping(value = "/login")
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

    /**
     * 根据主键删除用户
     *
     * @param userId 用户ID
     * @return 状态码信息
     */
    @DeleteMapping("/deleteById/{userId}")
    @ResponseBody
    public ReturnResult deleteByPrimaryKey(@PathVariable("userId") Integer userId) {
        return returnResult.getReturnResult(wjUserService.deleteByPrimaryKey(userId), returnResult, userId, "删除");
    }

    /**
     * 插入完整用户数据
     *
     * @param record 完整用户数据
     * @return 状态码信息
     */
    @PostMapping("/insertAll")
    @ResponseBody
    public ReturnResult insert(WJUser record) {
        return returnResult.getReturnResult(wjUserService.insert(record),
                returnResult, record, "插入");
    }

    /**
     * 插入部分用户数据
     *
     * @param record 部分用户数据
     * @return 状态码信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public ReturnResult insertSelective(WJUser record) {
        return returnResult.getReturnResult(wjUserService.insertSelective(record), returnResult, record, "插入");
    }

    /**
     * 根据主键查询用户信息
     *
     * @param userId 用户ID
     * @return 状态码信息
     */
    @GetMapping("/selectOne/{userId}")
    @ResponseBody
    public ReturnResult selectByPrimaryKey(@PathVariable("userId") Integer userId) {
        WJUser wjUser = wjUserService.selectByPrimaryKey(userId);
        return returnResult.getSelectReturnResult(returnResult, wjUser, "查询");
    }

    /**
     * 更新部分用户数据
     *
     * @param record 部分用户数据
     * @return 状态码信息
     */
    @PutMapping("/update")
    @ResponseBody
    public ReturnResult updateByPrimaryKeySelective(WJUser record) {
        return returnResult.getReturnResult(wjUserService.updateByPrimaryKeySelective(record), returnResult, record, "修改");
    }

    /**
     * 更新完整用户数据
     *
     * @param record 完整用户数据
     * @return 状态码信息
     */
    @PutMapping("/updateAll")
    @ResponseBody
    public ReturnResult updateByPrimaryKey(WJUser record) {
        return returnResult.getReturnResult(wjUserService.updateByPrimaryKey(record), returnResult, record, "修改");
    }
}
