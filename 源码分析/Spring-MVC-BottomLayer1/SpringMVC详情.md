
## 一、流程
1、启动 Tomcat
2、解析 web.xml
3、创建并初始化 DispatcherServlet
4、调用 DispatcherServlet 的 init() 方法：（DispatcherServlet实现了 Servlet 接口）

```java
/*********************************** HttpServletBean.java *******************************/
    /**
     * 重写了父类HttpServlet的init方法，进行配置文件的读取和MVC的初始化
     */
    public final void init() throws ServletException {
        // 解析 init-param 并封装到 PropertyValues 对象中
        PropertyValues pvs = new ServletConfigPropertyValues(getServletConfig(), this.requiredProperties);
        if (!pvs.isEmpty()) {
            try {
                // 将当前的这个 Servlet 类转化为一个 BeanWrapper，从而能够以 Spring 的方法来对 init-param 的值进行处理
                BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(this);
                ResourceLoader resourceLoader = new ServletContextResourceLoader(getServletContext());
                bw.registerCustomEditor(Resource.class, new ResourceEditor(resourceLoader, getEnvironment()));
                initBeanWrapper(bw);
                // 有点长，直接跟进去到BeanWrapper包装接口实现类BeanWrapperImpl中看setValue
                // 将配置文件设置到上下文中，以备后面解析
                bw.setPropertyValues(pvs, true);
            }
            catch (BeansException ex) {
                if (logger.isDebugEnabled()) {
                    logger.error("Failed to set bean properties on servlet '" + getServletName() + "'", ex);
                }
                throw ex;
            }
        }
        // 进入正题：初始化Servlet，创建 Spring 容器
        initServletBean();
    }
```

```java
/*********************************** FrameworkServlet.java *******************************/ 
    protected final void initServletBean() throws ServletException {
        // 这部分就是打印日志，记录执行时间戳 .........略
        
        try {
            // 重点在这里，初始化 SpringWeb 容器上下文信息
            this.webApplicationContext = initWebApplicationContext();
            // 空壳方法，可用于拓展
            initFrameworkServlet();
        }
        //..........................................略
    }

/*********************************** FrameworkServlet.java *******************************/ 
    protected WebApplicationContext initWebApplicationContext() {
        // 获取 SpringWeb 容器初始化时候的上下文信息
        // 获得 ContextLoaderListener 存的父容器
        WebApplicationContext rootContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        WebApplicationContext wac = null;

        if (this.webApplicationContext != null) {
            // 获得子容器
            wac = this.webApplicationContext;
            if (wac instanceof ConfigurableWebApplicationContext) {
                ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) wac;
                if (!cwac.isActive()) {
                    // 如果没有设置父容器  Spring  doGetBean
                    if (cwac.getParent() == null) {
                        cwac.setParent(rootContext);
                    }
                    // 这个地方避免自定义上下文监听没有进行IOC容器初始化，再一次初始化
                    // 配置并且加载子容器
                    configureAndRefreshWebApplicationContext(cwac);
                }
            }
        }
        if (wac == null) {
            // 判断是否已经初始化过了web容器上下文
            // 从 Servlet 上下文根据 <ContextAttribute> 名字从域里面获取
            wac = findWebApplicationContext();
        }
        if (wac == null) {
            // 开始初始化web容器上下文，过程跟IOC一样，调用了AbstractApplicationContext refresh方法
            // 然后在后置处理器中进行url和controller的绑定
            // 传入rootContext，在此基础上继续加载MVC资源
            // xml 会在这里创建
            wac = createWebApplicationContext(rootContext);
        }
        // refreshEventReceived 它会在容器加载完设置为true（通过事件 onApplicationEvent）
        if (!this.refreshEventReceived) {
            synchronized (this.onRefreshMonitor) {
                // 开始初始化 SpringMVC 九大组件
                onRefresh(wac);
            }
        }
        if (this.publishContext) {
            // Publish the context as a servlet context attribute.
            String attrName = getServletContextAttributeName();
            getServletContext().setAttribute(attrName, wac);
        }
        return wac;
    }

/*********************************** FrameworkServlet.java *******************************/
    protected WebApplicationContext createWebApplicationContext(@Nullable ApplicationContext parent) {
        // 获取SpringMVC容器：如果存在自定义的容器配置<param-name>contextClass</param-name>，那么这里返回的是自定义的容器类。
        //                  如果没有进行容器配置，则会使用默认容器（DEFAULT_CONTEXT_CLASS = XmlWebApplicationContext.class）
        Class<?> contextClass = getContextClass();
        if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
            throw new ApplicationContextException(
                    "Fatal initialization error in servlet with name '" + getServletName() +
                            "': custom WebApplicationContext class [" + contextClass.getName() +
                            "] is not of type ConfigurableWebApplicationContext");
        }
        // 实例化SpringMVC容器指定的类
        ConfigurableWebApplicationContext wac =
                (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

        wac.setEnvironment(getEnvironment());
        // 将IOC容器作为 SpringWeb 容器的父容器
        wac.setParent(parent);
        // 获取到Web配置信息：即 web.xml 中对应的<param-value>值
        String configLocation = getContextConfigLocation();
        if (configLocation != null) {
            wac.setConfigLocation(configLocation);
        }
        
        // 调用IOC容器初始化流程，实例化并依赖注入，初始化及回调后置处理器
        // 解析扫描resource包下的配置文件 springmvc.xml
        configureAndRefreshWebApplicationContext(wac);
        return wac;
    }
    
/*********************************** FrameworkServlet.java *******************************/
    protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac) {
        if (ObjectUtils.identityToString(wac).equals(wac.getId())) {
            // The application context id is still set to its original default value
            // -> assign a more useful id based on available information
            if (this.contextId != null) {
                wac.setId(this.contextId);
            }
            else {
                // Generate default id...
                wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX +
                        ObjectUtils.getDisplayString(getServletContext().getContextPath()) + '/' + getServletName());
            }
        }
        // 把之前ContextLoaderListener加载的web容器信息设置到WebApplicationContext
        wac.setServletContext(getServletContext());
        wac.setServletConfig(getServletConfig());
        wac.setNamespace(getNamespace());
        // 请记住这个地方，Spring在上下文中添加了一个监听事件SourceFilteringListener，当IOC初始化完成后通过广播事件触发该监听，进行MVC九大组件的初始化
        // 监听器   委托设计模式 
        // new ContextRefreshListener() ----> DispatcherServlet【onRefresh() ----> initStrategies(context)】
        wac.addApplicationListener(new SourceFilteringListener(wac, new ContextRefreshListener()));

        // 将 init-param 设置到 Environment 中
        ConfigurableEnvironment env = wac.getEnvironment();
        if (env instanceof ConfigurableWebEnvironment) {
            ((ConfigurableWebEnvironment) env).initPropertySources(getServletContext(), getServletConfig());
        }
        // 空方法可扩展
        postProcessWebApplicationContext(wac);
        // 容器启动前初始化
        applyInitializers(wac);
        // 接下来就是IOC的流程了
        wac.refresh();
    }
```

