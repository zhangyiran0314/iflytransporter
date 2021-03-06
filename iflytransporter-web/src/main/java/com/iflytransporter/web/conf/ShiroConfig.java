package com.iflytransporter.web.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iflytransporter.web.shiro.MShiroFilterFactoryBean;
import com.iflytransporter.web.shiro.PermissionFilter;
import com.iflytransporter.web.shiro.ShiroDbRealm;

@Configuration
public class ShiroConfig {
	private final static Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
	
	/**配置ehcache缓存*/
	@Bean
	public EhCacheManager getEhCacheManager(){
		EhCacheManager em =new EhCacheManager();
		//em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}
	@Bean(name="shiroDbRealm")
	public ShiroDbRealm shiroDbRealm(EhCacheManager cacheManager){
		ShiroDbRealm realm = new ShiroDbRealm();
		return realm;
	}
	@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
	@Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroDbRealm shiroDbRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(shiroDbRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 --> 
//       dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
	/**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
       /* Map<String,Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("perms",permissionFilter());
        shiroFilterFactoryBean.setFilters(filterMap);*/
        
//        filterChainDefinitionMap.put("/user", "authc");
        filterChainDefinitionMap.put("/user", "authc");
        filterChainDefinitionMap.put("/menu", "authc");
        filterChainDefinitionMap.put("/index", "authc");
        // anon：它对应的过滤器里面是空的,什么都没做
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "anon");//anon 可以理解为不拦截
     
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
        // 必须设置 SecurityManager  
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/user");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }
    @Bean
    public PermissionFilter permissionFilter()
    {
        return new PermissionFilter();
    }
}
