package com.example.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

// 自定义的Realm，用来实现用户的认证和授权
// 父类 AuthenticatingRealm只负责用户认证(登录)
// 父类 AuthorizingRealm既负责用户认证(登录)，又负责用户授权的
public class MyRealm extends AuthorizingRealm {

    /**
     * 用户授权的方法，当用户认证通过每次访问需要授权的请求时都需要执行这段代码来完成授权操作
     * 这里应该查询数据库来获取当前用户的所有角色和权限，并设置到shiro中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=====我正在授权哦！=========");
        Object obj = principals.getPrimaryPrincipal(); // 获取用户的账号，根据获取数据的账号来从数据库中获取
        // 定义用户角色的set集合，这个集合应该来自数据库
        /*
           注意：由于每次点击需要授权的请求时，Shiro都会执行这个方法，因此如果这里的数据是来自数据库中的
                那么一定要控制好不能每次都从数据库中获取数据，这样效率太低了
         */
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        // 设置角色，这里的操作应该是从数据库中读取数据。像："admin","user"...
        if("admin".equals(obj)){
            roles.add("admin");
            permissions.add("admin:add");
        }
        if("user".equals(obj)){
            roles.add("user");
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles); // 设置角色信息
        info.setStringPermissions(permissions); // 设置权限信息
        return info;
    }

    /**
     * 用户认证的方法  这个方法不用手动调用 Shiro会自动调用
     * @param authenticationToken 用户身份 这里存放着用户的帐号和密码
     * @return 用户登陆成功后的身份证明
     * @throws AuthenticationException 如果认证失败 Shiro会抛出各种异常
     *
     * 常用异常：
     *    UnknownAccountException：账号不存在
     *    LockedAccountException：账号异常
     *    IncorrectCredentialsException：账户锁定异常(冻结异常)
     *    AuthenticationException：密码认证失败以后shiro自动抛出表示密码错误
     * 注意：
     *    如果这些异常不够用可以自定义异常类并继承Shiro认证异常父类AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername(); // 获取页面中传递的用户账号
        String password = new String(token.getPassword()); // 获取页面中的用户密码。实际工作中基本不需要获取
        System.out.println(username + "---" + password);

        if(!"admin".equals(username) && !"zhangsan".equals(username) && !"user".equals(username)){
            throw new UnknownAccountException(); // 抛出账号错误的异常
        }
        if("zhangsan".equals(username)){
            throw new LockedAccountException(); // 抛出账号锁定异常
        }

        /*
          数据密码加密主要是防止数据在浏览器到后台服务器之间的数据传递时被篡改或被截获，因此应该在前端到后台的过程中进行加密，
          而我们这里的加密一个事件，将浏览器中获取后台的明码加密和对数据库中的数据进行加密
          // 认证帐号，这里应该从数据库中获取数据
          HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
          credentialsMatcher.setHashAlgorithmName("MD5");
          credentialsMatcher.setHashIterations(2);
          this.setCredentialsMatcher(credentialsMatcher);
          // 对数据库中的密码进行加密
          Object obj = new SimpleHash("MD5","123456","",2);
        */

        return new SimpleAuthenticationInfo(username, "e10adc3949ba59abbe56e057f20f883e", getName());
            /*
               创建密码认证对象，由Shiro自动认证密码
               参数1：数据库中的账号(或页面账号均可)
               参数2：为数据库中读取数据来的密码
               参数3：为当前Realm的名字
             */
    }

    public static void main(String[] args) {
        /*
          创建密码认证对象，由Shiro自动认证密码
            参数1：为加密的算法名 我们使用MD5这是一个不可逆的加密算法
            参数2：为需要加密的数据
            参数3：加密的盐值  用于改变加密结果的  不同的盐加密的数据是不一致的
            参数4：为加密的次数
         */
        Object obj = new SimpleHash("MD5","123456", "", 1);
        System.out.println("123456使用MD5加密1次--" + obj);

        Object obj2 = new SimpleHash("MD5", "123456", "", 2);
        System.out.println("123456使用MD5加密2次--" + obj2);

        Object obj3 = new SimpleHash("MD5", "e10adc3949ba59abbe56e057f20f883e", "", 1);
        System.out.println("123456使用MD5加密1次后再对这个数据加密一次--" + obj3);

        Object obj4 = new SimpleHash("MD5", "123456", "admin", 1);
        System.out.println("123456使用MD5 加盐admin 加密1次--" + obj);
    }
}
