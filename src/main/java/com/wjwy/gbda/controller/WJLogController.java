package com.wjwy.gbda.controller;

import com.wjwy.gbda.entity.WJLog;
import com.wjwy.gbda.entity.WJUser;
import com.wjwy.gbda.service.WJLogService;
import com.wjwy.gbda.util.GetIp;
import com.wjwy.gbda.util.SystemLog;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping(value = "/log")
@RestController
@EnableTransactionManagement
@Aspect
public class WJLogController {
    @Resource
    @Getter
    @Setter
    private WJLogService wjLogService;

    //配置切入点
    @Pointcut("@annotation(com.wjwy.gbda.util.SystemLog)")
    private void controllerAspect() {
    }//定义一个切入点

    //方法执行后的操作
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //常见日志实体对象
        WJLog log = new WJLog();
        ////获取登录用户账户
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        WJUser wjUserVO =
                (WJUser) request.getSession().getAttribute("UserVO");
        if (wjUserVO != null) {
            //成功获取到VO
            log.setLogUserName(wjUserVO.getUserName());
        } else if (request.getParameter("user_name") != null) {
            //获取VO失败
            log.setLogUserName(request.getParameter("user_name"));
        } else {
            //未登录
            log.setLogUserName("未登录用户");
        }
        //获取系统时间
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date());
        log.setTime(time);
        log.setUpdateTime(time);
        //获取系统ip
        String ip = GetIp.getIpAddr(request);
        log.setIp(ip);
        // 拦截的实体类，就是当前正在执行的action
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        Object object = null;
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setDescription(systemlog.methods());
                log.setLogNum(systemlog.log_num());
                try {
                    object = pjp.proceed();
                    log.setCommite("执行成功！");
                    //执行成功保存进数据库
                    wjLogService.insert(log);
                } catch (Throwable e) {
                    log.setCommite("执行失败");
                    //执行失败保存进数据库
                    wjLogService.insert(log);
                }
            } else {//没有包含注解
                object = pjp.proceed();
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
        }
        return object;
    }
}
