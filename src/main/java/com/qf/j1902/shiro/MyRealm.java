package com.qf.j1902.shiro;

import com.qf.j1902.pojo.Perms;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.perms;

/**
 * Created by Qxk on 2019/6/10.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String)principalCollection.getPrimaryPrincipal();
        List<String> strings = userService.findmenuName(username);
        Set<String> perm=new HashSet<>();

        if(perms!=null){
            for (String S:strings ) {
                String permission =S;
                perm.add(permission);
            }


        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(perm);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User oneByName = userService.findOneByName(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo =null;
        if(oneByName!=null){
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, oneByName.getPassword(),this.getName());

        }

        return simpleAuthenticationInfo;
    }
}
