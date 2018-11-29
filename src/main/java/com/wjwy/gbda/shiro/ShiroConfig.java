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
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie sessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setHttpOnly(true);
        sessionIdCookie.setMaxAge(-1);
        return sessionIdCookie;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie rememberMeCookie() {
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        rememberMeCookie.setHttpOnly(true);
        rememberMeCookie.setMaxAge(2592000);// 记住我cookie生效时间30天 ,单位秒
        return rememberMeCookie;
    }

    @Bean(name = "passwordMatcher")
    public CustomCredentialsMatcher passwordMatcher() {
        return new CustomCredentialsMatcher();
    }

    @Bean(name = "formAuthenticationFilter")
    public FormAuthenticationFilter formAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter =
                new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("userName");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        formAuthenticationFilter.setLoginUrl("/login");
        return formAuthenticationFilter;
    }

    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager shiroEhcacheManager() {
        EhCacheManager shiroEhcacheManager = new EhCacheManager();
        shiroEhcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return shiroEhcacheManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "defaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager(@Qualifier(
            "rememberMeCookie") SimpleCookie rememberMeCookie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(rememberMeCookie);
        return rememberMeManager;
    }

    @Bean(name = "sessionDAO")
    public EnterpriseCacheSessionDAO sessionDAO(@Qualifier("sessionIdGenerator") JavaUuidSessionIdGenerator sessionIdGenerator) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        return sessionDAO;
    }

    @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm(@Qualifier("passwordMatcher") CustomCredentialsMatcher passwordMatcher) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(passwordMatcher);
        shiroRealm.setAuthenticationCachingEnabled(true);
        shiroRealm.setAuthenticationCacheName("authenticationCache");
        shiroRealm.setAuthorizationCacheName("authorizationCache");
        return shiroRealm;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager(@Qualifier("sessionDAO") EnterpriseCacheSessionDAO sessionDAO, @Qualifier("sessionIdCookie") SimpleCookie sessionIdCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1200000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(sessionIdCookie);
        return sessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm, @Qualifier("sessionManager") DefaultWebSessionManager sessionManager, @Qualifier("shiroEhcacheManager") EhCacheManager shiroEhcacheManager, @Qualifier("rememberMeManager") CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(shiroEhcacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/views/login.html");
        shiroFilter.setUnauthorizedUrl("/user/error");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/index", "anon");
        filterChainDefinitionMap.put("/user/indexLogin", "anon");
        //filterChainDefinitionMap.put("/loginUser", "anon");
        //filterChainDefinitionMap.put("/admin", "roles[admin]");
        //filterChainDefinitionMap.put("/edit", "perms[delete]");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }
}
