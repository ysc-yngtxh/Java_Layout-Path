package com.example.realm;

import com.example.service.UserService;
import com.example.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class RealmUser extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /*springboot集成shiro一定要加上这个，不然会报错。我找错误找了一个星期，
    期间还无数次熬夜通宵，很多次想放弃，皇天不负有心人，终于找到了这个😭😭😭😭😭*/
    /*@Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }*/

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了--doGetAuthorizationInfo授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加授权，但是这种固定死了。我们需要在数据库中取
        //info.addStringPermission("user:add");

        //获取当前用户对象
        Subject subject = SecurityUtils.getSubject();
        //通过当前用户对象获取在认证中的参数一，进而获取数据库对象
        User principal = (User)subject.getPrincipal();


        //设置当前用户权限
        info.addStringPermission(principal.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("执行了--doGetAuthenticationInfo认证");

        //通过token令牌获取到用户信息
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        User query = userService.queryUser(userToken.getUsername());

        if(null == query){
            throw new UnknownAccountException();
        }


        //这里的话是用于在登陆后回到首页，没有登录标志
        Subject subject1 = SecurityUtils.getSubject();
        Session session = subject1.getSession();
        session.setAttribute("loginuser",query);


        return new SimpleAuthenticationInfo(query,query.getPwd(),"");
        /*
           参数一：用户的映射资源对象
           参数二：认证用户密码
           参数三：realm名字，随便取
        */
    }
}
