package com.wjwy.gbda.shiro;

import com.wjwy.gbda.util.CustomCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    /**
     * 会话ID生成器
     *
     * @return 会话ID生成器
     */
    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 注册全局异常处理
     *
     * @return MyExceptionHandler
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }

    /**
     * 会话Cookie模板
     *
     * @return 会话Cookie模板
     */
    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);
        return sessionIdCookie;
    }

    /**
     * 记住密码持久cookie设置
     *
     * @return 记住密码持久cookie
     */
    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        rememberMeCookie.setHttpOnly(true);
        rememberMeCookie.setMaxAge(2592000);// 记住我cookie生效时间30天 ,单位秒
        return rememberMeCookie;
    }

    /**
     * 设置密码加密策略 md5hash
     *
     * @return 密码加密策略 md5hash
     */
    @Bean(name = "passwordMatcher")
    public CustomCredentialsMatcher passwordMatcher() {
        return new CustomCredentialsMatcher();
    }

    /**
     * 基于Form表单的身份验证过滤器
     *
     * @return 基于Form表单的身份验证过滤器
     */
    @Bean(name = "formAuthenticationFilter")
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter =
                new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("userName");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        formAuthenticationFilter.setLoginUrl("/user/login");
        return formAuthenticationFilter;
    }

    /**
     * 用户授权/认证信息Cache, 采用EhCache  缓存
     *
     * @return EhCache  缓存
     */
    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager shiroEhcacheManager() {
        EhCacheManager shiroEhcacheManager = new EhCacheManager();
        shiroEhcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return shiroEhcacheManager;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     *
     * @return lifecycle函数
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * rememberMe管理器
     *
     * @param rememberMeCookie 记住密码持久cookie设置
     * @return rememberMe管理器
     */
    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(@Qualifier(
            "rememberMeCookie") SimpleCookie rememberMeCookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(rememberMeCookie);
        return rememberMeManager;
    }

    /**
     * 会话DAO
     *
     * @param sessionIdGenerator 会话ID生成器
     * @return 会话DAO
     */
    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO sessionDAO(@Qualifier("sessionIdGenerator") JavaUuidSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }

    /**
     * 自定义权限认证
     *
     * @param passwordMatcher 密码加密策略
     * @return 自定义权限认证
     */
    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm(@Qualifier("passwordMatcher") CustomCredentialsMatcher passwordMatcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(passwordMatcher);
        shiroRealm.setAuthenticationCachingEnabled(true);
        shiroRealm.setAuthenticationCacheName("authenticationCache");
        shiroRealm.setAuthorizationCacheName("authorizationCache");
        return shiroRealm;
    }

    /**
     * 会话管理器 ,时间单位是毫秒
     *
     * @param sessionDAO      会话DAO
     * @param sessionIdCookie 会话Cookie模板
     * @return 会话管理器
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(@Qualifier("sessionDAO") EnterpriseCacheSessionDAO sessionDAO, @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie) {
        //MySessionManager sessionManager=new MySessionManager(); //ajax登录使用
        DefaultWebSessionManager sessionManager =
                new DefaultWebSessionManager();//测试使用
        sessionManager.setGlobalSessionTimeout(1200000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(1200000);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        return sessionManager;
    }

    /**
     * 安全管理器
     *
     * @param shiroRealm          自定义权限认证
     * @param sessionManager      会话管理器
     * @param shiroEhcacheManager 缓存设置
     * @param rememberMeManager   rememberMe管理器
     * @return 安全管理器
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm, @Qualifier("sessionManager") DefaultWebSessionManager sessionManager, @Qualifier("shiroEhcacheManager") EhCacheManager shiroEhcacheManager, @Qualifier("rememberMeManager") CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(shiroEhcacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setRealm(shiroRealm);
        return securityManager;
    }

    /**
     * 开启springboot 注解支持
     * 生成代理，通过代理进行控制
     *
     * @return 注解支持
     */
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro过滤器
     *
     * @param securityManager 安全管理器
     * @return shiro过滤器配置
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/views/login.html");
        shiroFilter.setUnauthorizedUrl("/user/error");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/index", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
        filterChainDefinitionMap.put("/user/indexLogin", "anon");
        filterChainDefinitionMap.put("/user/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/logout", "logout");//shiro自带的logout退出过滤器
        //filterChainDefinitionMap.put("/loginUser", "anon");
        //filterChainDefinitionMap.put("/admin", "roles[admin]");
        //filterChainDefinitionMap.put("/edit", "perms[delete]");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }
}
