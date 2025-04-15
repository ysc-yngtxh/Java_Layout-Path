package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.lang.ShiroException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShiroController {

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        // 获取权限操作对象，利用这个对象来完成登陆操作
        Subject subject = SecurityUtils.getSubject();
        // 登出，进入这个请求用户一定是要完成用户登录功能。因此我们就先登出，否则Shiro会有缓存不能重新登陆
        subject.logout();
        // 用户是否认证过(是否登陆过)，进入if表示用户没有认证过需要进行认证
        if(!subject.isAuthenticated()) {
            // 创建用户认证时的身份令牌,并设置我们从页面中传递过来的账号和密码
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

            System.out.println("流程会走到这里吗？");
            try {
                subject.login(usernamePasswordToken);
            } catch(UnknownAccountException e) {
                model.addAttribute("errorMessage", "账号错误！");
                return "login";
            } catch (LockedAccountException e) {
                model.addAttribute("errorMessage", "账号被锁定！");
                return "login";
            } catch (IncorrectCredentialsException e) {
                model.addAttribute("errorMessage", "密码错误！");
                return "login";
            } catch (AuthenticationException e) {
                model.addAttribute("errorMessage", "认证失败！");
                return "login";
            }
        }
        return "redirect:/success";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        // 登陆当前帐号，清空shiro当前用户的缓存，否则无法重新登陆
        subject.logout();
        return "redirect:/";
    }

    @RequiresGuest
    @RequestMapping("/success")
    public String loginSuccess() {
        return "success";
    }

    @RequestMapping("/noPermission")
    public String noPermission() {
        return "noPermission";
    }

    /* @RequiresRoles 这个注解是Shiro提供的 用于标签类或当前在访问是必须需要什么样角色
     *  属性：
     *      value：取值为String 数组类型 用于指定访问时所需要的一个或多个角色名
     *      logical：取值为Logical.AND或Logical.OR,当指定多个角色时可以使用AND或OR来表示并且和或的意思默认值为AND
     *               表示当前用户必须同时拥有多个角色才可以访问这个方法。

     *   注意：Shiro中出现基于配置权限验证以及注解的权限验证以外还支持基于方法调用的权限验证
     *        例如：验证当前用户是否拥有指定的角色  验证当前用户是否拥有指定的权限
     */
    @RequiresRoles(value="admin")
    @RequestMapping("/admin/test")
    public @ResponseBody String adminTest() {
        Subject subject = SecurityUtils.getSubject();
        String[] roles = {"admin"};
        subject.checkRoles(roles); // 验证当前用户是否拥有指定的角色
        return "/admin/test请求";
    }

    @RequestMapping("/admin/test1")
    public @ResponseBody String adminTest1() {
        return "/admin/test1请求";
    }

    // @RequiresPermissions 用于判断当前用户是否有指定的一个或多个权限用法与@RequiresRoles 相同
    @RequiresPermissions(value="admin:add")
    @RequestMapping("/admin/test2")
    public @ResponseBody String adminTest2() {
        Subject subject = SecurityUtils.getSubject();
        String[] permission = {"admin:add"};
        subject.checkPermissions(permission); // 验证当前用户是否拥有指定的权限
        return "/admin/test2请求";
    }

    @RequiresRoles(value={"user"})
    @RequestMapping("/user/test")
    public @ResponseBody String userTest() {
        Subject subject = SecurityUtils.getSubject();
        String[] roles = {"user"};
        subject.checkRoles(roles); // 验证当前用户是否拥有指定的角色
        return "/user/test请求";
    }

    @ExceptionHandler(ShiroException.class)
    public String permissionError(Throwable throwable) {
        // 转向到没有权限的视图页面，可以利用参数throwable将错误信息写入到浏览器中
        // 实际工作中应该根据参数的类型来判断具体是什么异常，然后根据不同的异常来为用户提供不同的提示信息
        return "noPermission";
    }
}
