package com.example.springbootshirojwt.controller;

import com.example.springbootshirojwt.vo.BackResponse;
import com.example.springbootshirojwt.pojo.User;
import com.example.springbootshirojwt.service.ShiroJwtService;
import com.example.springbootshirojwt.shiro.JwtToken;
import com.example.springbootshirojwt.utils.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TokenController {

    @Autowired
    private ShiroJwtService shiroJwtService;
    @Autowired
    private BackResponse backResponse;


    @PostMapping("/login")
    public ResponseEntity<BackResponse> testdemo(@RequestBody User user, HttpServletRequest request) {
        /*
           我算是发现了，程序员的bug是一个接一个啊。这里登录还是需要进行认证授权工作的，必须是先进行认证身份后，才能进行后面的数据处理
           把shiro流程稍微说一下：
             首先path：/login的请求在ShiroFilterFactoryBean中是不需要进行认证和授权就能访问的(可以将shiro看作是拦截器)，
             但是登录访问路径肯定还是要身份信息是否正确，执行了--doGetAuthenticationInfo认证。
             你要知道，如果在登录路径不进行身份认证，那么其他的路径请求在都会ShiroFilterFactoryBean中因为未认证而重定向到其他路径，
             例如map.put("/user/ysc","roles[vip]");是需要认证并某个角色的权限才能访问的。
         */
        String username = user.getName();
        String password = user.getPwd();
        String code = user.getCode();
        String captcha = request.getSession().getAttribute("captcha").toString();
        User users = shiroJwtService.queryByName(username);

        if(null == users){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(backResponse.error(401,"用户不存在！！！"));
        }else if( !password.equals(users.getPwd()) ){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(backResponse.error(402,"密码错误，请重新输入！！！"));
        }else if( !code.equalsIgnoreCase(captcha) ){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(backResponse.error(403,"验证码错误！！！"));
        }
        String jwtsToken = JwtUtils.getJwtsToken(username, password);
        return ResponseEntity.status(HttpStatus.OK)
                .body(backResponse.success(200,"登陆成功！！！",jwtsToken));
    }

    @RequestMapping("/user/you")
    public BackResponse registers() {

        return backResponse.success(200,"跳转成功！");

    }

    @PostMapping("/user/ysc")
    public BackResponse register(HttpServletRequest request) {

        //盐 + 输入的密码(注意不是用户的正确密码) + 1024次散列，作为token生成的密钥
        //Md5Hash md5Hash = new Md5Hash(admin.getPassword());

        //通过请求体中的Header获取到浏览器传过来的Token
        String authorization = request.getHeader("Authorization");
        //将传过来String类型的Token转换成AuthenticationToken类型
        JwtToken token = new JwtToken(authorization);

        //封装用户的登录数据
        //UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPwd());

        //token.setRememberMe(true); //设置记住我

        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);//执行登陆方法,即Shiro开始进行认证授权的工作
            return backResponse.success(200,"认证成功！");
        } catch(Exception e){
            e.printStackTrace();
            return backResponse.error(404,"认证失败！");
        }
    }

    @RequestMapping("/Authorization")
    public ResponseEntity<BackResponse> sgf(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)//401
                .body(backResponse.error(110,"未经授权，请联系管理员！"));
    }

    @RequestMapping("/Authentication")
    public ResponseEntity<BackResponse> sgfg(){
        return ResponseEntity.status(HttpStatus.PROXY_AUTHENTICATION_REQUIRED)//407
                .body(backResponse.error(111,"未进行登录认证，请重新登陆！"));
    }



}

