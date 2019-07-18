package com.qf.j1902.config;

import com.qf.j1902.shiro.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Qxk on 2019/6/10.
 */
@Configuration
public class ShiroConfig {
    //    创建自定义的realm对象
    @Bean(name="myRealm")
    public MyRealm getRealm(HashedCredentialsMatcher credentialsMatcher){
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }
    //    创建安全管理器
    @Bean(name="defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myRealm){
//       创建secuManager对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        设置自定义的realm
        securityManager.setRealm(myRealm);
        return securityManager;
    }
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl("/dengLu");//登录页
        filterFactoryBean.setUnauthorizedUrl("/unauth");//无权访问时显示的页面
        Map<String ,String> map = new HashMap<>();
        map.put("/main","authc");//只有登录后才可访问
        map.put("/yongHuWeiHu","authc");
        map.put("/yongHuWeiHu","perms[用户维护]");
        map.put("/jiaoSeWeiHu","perms[角色维护]");
        map.put("/xuKeWeiHu","perms[许可维护]");
        // map.put("/one","perms[user_edit]");//用户登录成功后，并且拥有user_edit权限方可访问
        //map.put("/two","perms[user_forbidden]");//用户登录成功后，并且拥有user_forbidden权限方可访问
        filterFactoryBean.setFilterChainDefinitionMap(map);//设置拦截权限
        return filterFactoryBean;
    }
   @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5") ;//设置加密算法，还可以是SHA1
      //  credentialsMatcher.setHashIterations(1024);//设置密码算法的hash频率
        credentialsMatcher.setStoredCredentialsHexEncoded(true);//十六进制字符串

        return credentialsMatcher;
    }
}
