
## 一、入门案例认证流程
    接口:                                       实现类:
       AbstractAuthenticationProcessingFilter       UsernamePasswordAuthenticationFilter
           ⬇︎                                           ⬇︎
       AuthenticationManager                        ProviderManager
           ⬇︎                                           ⬇︎
       AuthenticationProvider                       DaoAuthenticationProvider
           ⬇︎                                           ⬇︎
       UserDetailsService                           InMemoryUserDetailsManager（JdbcUserDetailsManager）

    UsernamePasswordAuthenticationFilter
        |- attemptAuthentication()方法尝试认证
              |- UsernamePasswordAuthenticationToken包装用户信息
                    |- AuthenticationManager
                          |- authenticate()方法有一个for循环来执行 AuthenticationProvider
                                |- AuthenticationProvider
                                      |- authenticate()方法
                                            |- retrieveUser()方法检索用户名
                                                 |- UserDetailsService
                                                       |- loadUserByUsername()方法
                                            |- additionalAuthenticationChecks()方法检索密码

  - ### 表单登录的认证流程
        1、表单提交用户名、密码
        2、表单的请求被 UsernamePasswordAuthenticationFilter 捕获，调用 attemptAuthentication 方法执行响应
        3、将用户名和密码封装到 Authentication（实现类[UsernamePasswordAuthenticationToken]）对象之中。这时候只有用户名和密码，权限还没有。
        4、AuthenticationManager 是认证管理器，它通过 authenticate() 方法发起委托认证，之后由 ProviderManager 根据参数类型，匹配合适的 AuthenticationProvider
        5、AuthenticationProvider 是认证类的提供者，其实现类 DaoAuthenticationProvider 专用于处理 UsernamePasswordAuthenticationToken 的认证请求
        6、DaoAuthenticationProvider 认证类执行 authenticate() 方法，其内部执行以下操作：
           (1)、调用 retrieveUser() 方法实现对用户名的检索。
                retrieveUser()方法中调用 UserDetailsService类的 loadUserByUsername()返回 UserDetails 对象
                ①、UserDetailsService 是用户信息的业务类。
                   Ⅰ、其实现类 InMemoryUserDetailsManager 专用于从内存中检索用户信息
                   Ⅱ、另外，可自定义其实现类，从数据库中检索用户信息
                ②、UserDetailsService 的实现类功能：
                   Ⅰ、根据用户名去查询对应的用户以及这个用户对应的权限信息，InMemoryUserDetailsManager 是在内存中查找，自定义实现类是在数据库中查找。
                   Ⅱ、把对应的用户信息包括去权限信息封装成 UserDetails 对象返回
                ③、UserDetails 是用户信息接口，其实现类 User 封装了用户信息，包括用户名、密码、权限信息等。
           (2)、调用 additionalAuthenticationChecks() 方法实现对密码的检索，
                通过 PasswordEncoder 对比 UserDetails 中的密码和 Authentication 的密码是否正确。
           (3)、如果上述的用户名与密码检索都正确，就把 UserDetails 中的权限信息封装到 Authentication（实现类[UsernamePasswordAuthenticationToken]）对象中
        7、返回 Authentication 对象给实现类 UsernamePasswordAuthenticationFilter
           (1)、返回的 Authentication 对象就使用 SecurityContextHolder.getContext().setAuthentication() 方法
                存储到 SecurityContextHolder 对象中，供后续Security的Filter使用。其他过滤器会通过 SecurityContextHolder 来获取当前用户信息。


## 二、CSRF攻击(跨域请求伪造 Cross-site request forgery)
        比如说，我们的有一个不是前后端分离的项目系统A，前后端都是通过Cookie或者JSESSIONID来校验用户信息的，
    有一天我通过这个A系统给自己的的女朋友转账（转账行为看成是C），转了大概有十个小目标。
    这个时候外部有一个钓鱼网站系统B，我在不知道是非法网站的的情况下点击了这个网站，
    这个网站就携带了我当前用户系统A的Cookie或者JSESSIONID去进行转账（也就是去操作C），然后转账成功了，
    因为发起转账请求的是自己的Cookie或者JSESSION，A系统验证了该用户身份信息（老铁没毛病）。
    其实就是因为 钓鱼网站系统B 伪造了我的请求，携带我的 Cookie 信息去进行访问而造成的。


        因此，为了避免这种攻击，Spring Security提供了防止CSRF攻击的手段。
    CSRF为了保证不是其他第三方网站访问，要求访问时携带参数名为 _csrf 值为token（token在服务端产生）的内容，
    如果token和服务端的token匹配成功，则正常访问。

    流程：
        1、生成CSRF Token：
           当用户访问受保护的页面时，Spring Security会为该会话生成一个唯一的CSRF token。
           这个token被存储在服务器端（通常是与HttpSession关联），并且也会传递给客户端(Cookie或者视图参数_csrf)。
        2、包含CSRF Token在请求中：
           当客户端发出POST、PUT、DELETE等可能会修改服务器状态的请求时，需要将CSRF token包含在请求中。
           对于传统的HTML表单提交，CSRF token通常作为一个隐藏字段(_csrf=解析的XSRF-TOKEN值)包含在表单中。
           对于AJAX请求，CSRF token通常添加到HTTP头部，如X-XSRF-TOKEN。
        3、验证CSRF Token：
           CsrfFilter会在处理每个可能改变状态的请求之前检查请求中的CSRF token。
           它会比较来自请求的CSRF token和服务器端存储的token是否匹配。
           如果token匹配，则请求被认为是合法的，允许继续处理；如果不匹配，请求将被拒绝。
        4、处理结果：
           如果验证成功，请求将被转发到下一个过滤器或目标资源。
           如果验证失败，将会抛出异常，默认情况下会导致403 Forbidden HTTP状态码响应。

        Spring Security的这个CSRF方式跟我们的Jwt就是同一个原理。
    如果无状态 API 使用基于 Token 的身份验证（如 JWT），就不需要 CSRF 保护，前后端分离项目天生就预防了CSRF攻击。
    反之，如果使用 Session Cookie 进行身份验证，就需要启用 CSRF 保护。