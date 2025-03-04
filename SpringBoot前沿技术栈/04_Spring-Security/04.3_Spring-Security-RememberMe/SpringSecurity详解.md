
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

## 三、Remember Me（记住我）功能
    Ⅰ、简介：  
       1、用户登录成功后，Security会往用户浏览器中写入一个可以为 remember-me 的 Cookie，其值经过了Base64的编码。
       2、当用户重新访问同一个 Web 应用时，如果 Cookie 没有过期，那么用户可以直接登录到系统中，而无需重新执行登录操作。
       3、实现原理：
          用户登录成功后，Security会往用户浏览器中写入一个可以为 remember-me 的 Cookie，其值经过了Base64的编码。
          经过解码后得到的原始值分为四段，使用":"分割。
             base64(username + ":" + expirationTime + ":" + algorithmName + ":" 
                    + algorithmHex(username+":"+expirationTime+":"password+":"+key))
             四段分别表示的含义：用户名 : 过期时间戳 : 加密算法名 :
                              签名字符串(该字符串由用户名、过期时间戳、密码、开发指定的key值四者结合按照算法生成十六进制的加密字符串)
       4、RememberMe的Cookie中保存了用户名和密码等敏感信息，虽然加密处理，仍然有被破解的可能。
          在使用JWT实现登录认证之后，就不必使用RememberMe的功能了。
   
    Ⅱ、Remember Me 写入Cookie流程：
       AbstractAuthenticationProcessingFilter --> doFilter()方法  --> successfulAuthentication()方法
             | RememberMeServices --> loginSuccess()方法
                   | AbstractRememberMeServices --> onLoginSuccess()方法
                         | TokenBasedRememberMeServices
    
    Ⅲ、Remember Me 读取Cookie流程：
       RememberMeAuthenticationFilter --> doFilter()方法
             | RememberMeServices --> autoLogin()方法
                   | AbstractRememberMeServices --> processAutoLoginCookie()方法
                         | TokenBasedRememberMeServices

    Ⅳ、"记住我" 有两个具体的实现。  
        1、使用散列法来保护基于cookie的令牌的安全性    
            ①、这种方式不需要在服务器端存储任何信息。它通过将用户的用户名、过期时间和一个密钥进行哈希计算，生成一个签名。
               这个签名会被设置为一个 cookie 发送到用户的浏览器。当用户下次访问应用时，系统会验证这个签名的有效性。
               如果签名有效，则自动登录用户。
            ②、此方式可以将cookie获取到之后放到另一个客户端照样可以访问，而且不会修改cookie值，很不安全。
               如果需要使用记住我功能可以通过持久化令牌方式会安全一点，但也不能保障完全安全哦！
        2、使用数据库或其他持久性存储机制来存储生成的令牌。
            ①、当用户选择“记住我”选项并成功登录后，系统会生成一个唯一的凭证(token)，并将该令牌存储在数据库中。
               同时，这个令牌会被设置为一个 cookie 发送到用户的浏览器。当用户下次访问应用时，即使没有显式登录，
               系统也会检查这个 cookie 中的令牌，并与数据库中的令牌进行匹配。如果匹配成功，则自动登录用户
            ②、持久化方法能够有效地防止同一个 token 的重复使用。因为每次登录都会创建一个新的系列号和令牌对，
               并且旧的会被标记为已用或删除。
        
    Ⅴ、实现方式区别：
            简单的哈希令牌法，如果在令牌有效期间内攻击者获取到 Cookie 中的令牌，那么相应的用户是没办法下线和拒绝伪造的请求。
            持久化令牌法可以解决这个问题：相应的用户可以进行重新登录，那么在数据库表中的（series 和 token）会进行刷新，
                                     旧的（series 和 token）会被标记为已用或删除。
   - #### 1、基于哈希简单令牌法
   - ```java
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                // 配置"记住我"功能
                .rememberMe(remember ->
                        // 指定Cookie中生成的 remember-me 加密需要的key值，使其编码更加难以被破解
                        remember.key("yjwk")
                                // 修改登陆表单中remember复选框的name值，其默认参数为remember-me
                                .rememberMeParameter("rememberMe")
                                // 修改Cookie中的"记住我"值，其默认参数为remember-me
                                .rememberMeCookieName("rememberMe")
                                // 设置 token 的有效时间(单位：秒)。Security默认时长为14天(两周)
                                .tokenValiditySeconds(30)
                )
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                .logout(Customizer.withDefaults());
   
            return http.build();
        }
     ```
   - #### 2、持久化令牌法
   - ```java
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http
                  .cors(AbstractHttpConfigurer::disable)
                  .csrf(AbstractHttpConfigurer::disable)
                  .formLogin(Customizer.withDefaults())
                  // 配置"记住我"功能
                  .rememberMe(remember ->
                                  // 指定Cookie中生成的 remember-me 加密需要的key值，使其编码更加难以被破解
                                  remember.key("yjwk")
                                          // 修改登陆表单中remember复选框的name值，其默认参数为remember-me
                                          .rememberMeParameter("rememberMe")
                                          // 修改Cookie中的"记住我"值，其默认参数为remember-me
                                          .rememberMeCookieName("rememberMe")
                                          // 设置 token 的有效时间(单位：秒)。Security默认时长为14天(两周)
                                          .tokenValiditySeconds(30)
                                          // 设置操作数据表的 Repository
                                          .tokenRepository(persistentTokenRepository())
                  )
                  .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                  })
                  .logout(Customizer.withDefaults());
        
          return http.build();
        }
        
        @Resource
        private DataSource dataSource;
        
        /**
         * 配置 JdbcTokenRepositoryImpl，用于 Remember-Me 的持久化 Token
         */
        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
          JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
          // 赋值数据源
          jdbcTokenRepository.setDataSource(dataSource);
          // 自动创建表,第一次执行会创建，以后要执行就要删除掉！
          // jdbcTokenRepository.setCreateTableOnStartup(true);
          return jdbcTokenRepository;
        }
     ```