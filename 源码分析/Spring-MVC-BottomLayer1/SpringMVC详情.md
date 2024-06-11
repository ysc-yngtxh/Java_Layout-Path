
## 一、流程
1、启动 Tomcat
2、解析 web.xml 【<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>】
3、创建并初始化 DispatcherServlet
4、调用 DispatcherServlet 的 init 方法：
   DispatcherServlet继承FrameworkServlet，FrameworkServlet实现HttpServletBean，HttpServletBean继承HttpServlet
   ----> Tomcat启动后执行的 initServlet 方法，调用 Servlet 类中的 init 方法，HttpServletBean 实现 init方法，
   ----> HttpServletBean 调用 FrameworkServlet 实现的 initServletBean 方法
   ----> 在 initServletBean 方法中创建 SpringMVC 容器（FrameworkServlet类中的createWebApplicationContext方法） 
   ----> initStrategies(context)方法
5、SpringMVC 九种组件初始化策略
   ----> 1. 比如：执行 initHandlerMappings(context);
   ----> 2. 没有拿到 HandlerMapping，就会使用默认的 HandlerMapping。
            去找 DispatcherServlet.properties 文件中，获取 HandlerMapping 对应的 value 值。
   ----> 3. 比如：创建 RequestMappingHandlerMapping 的 Bean 对象
   ----> 4. 执行 afterPropertiesSet 方法

```java
/*********************************** HttpServletBean.java *******************************/
    /**
     * 重写了父类 HttpServlet 的init方法，进行配置文件的读取和MVC的初始化
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
        //..........................................略
        
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


## 二、SpringMVC 九种组件初始化策略。
```java
/*********************************** DispatcherServlet.java *******************************/
    private void initHandlerMappings(ApplicationContext context) {
		this.handlerMappings = null;

		if (this.detectAllHandlerMappings) {
			// 根据类型获取 Bean（可能会拿到多个）
			Map<String, HandlerMapping> matchingBeans =
					BeanFactoryUtils.beansOfTypeIncludingAncestors(context, HandlerMapping.class, true, false);
			if (!matchingBeans.isEmpty()) {
				this.handlerMappings = new ArrayList<>(matchingBeans.values());
				// We keep HandlerMappings in sorted order.
				AnnotationAwareOrderComparator.sort(this.handlerMappings);
			}
		}
        // 根据名字（唯一）
		else {
			try {
				HandlerMapping hm = context.getBean(HANDLER_MAPPING_BEAN_NAME, HandlerMapping.class);
				this.handlerMappings = Collections.singletonList(hm);
			}
			catch (NoSuchBeanDefinitionException ex) {
				// Ignore, we'll add a default HandlerMapping later.
			}
		}

		// 如果没有配，就去 DispatcherServlet.properties 拿默认的
		if (this.handlerMappings == null) {
			this.handlerMappings = getDefaultStrategies(context, HandlerMapping.class);
			if (logger.isTraceEnabled()) {
				logger.trace("No HandlerMappings declared for servlet '" + getServletName() +
						"': using default strategies from DispatcherServlet.properties");
			}
		}
        //..........................................略
	}
    
/*********************************** DispatcherServlet.java *******************************/
    protected <T> List<T> getDefaultStrategies(ApplicationContext context, Class<T> strategyInterface) {
        if (defaultStrategies == null) {
            try {
                // 通过 PropertiesLoaderUtils 工具类加载 DEFAULT_STRATEGIES_PATH = DispatcherServlet.properties
                ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH, DispatcherServlet.class);
                defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not load '" + DEFAULT_STRATEGIES_PATH + "': " + ex.getMessage());
            }
        }

        String key = strategyInterface.getName();
        String value = defaultStrategies.getProperty(key);
        if (value != null) {
            String[] classNames = StringUtils.commaDelimitedListToStringArray(value);
            List<T> strategies = new ArrayList<>(classNames.length);
            for (String className : classNames) {
                try {
                    Class<?> clazz = ClassUtils.forName(className, DispatcherServlet.class.getClassLoader());
                    // 通过 BeanFactory 创建容器 Bean，并进行初始化
                    Object strategy = createDefaultStrategy(context, clazz);
                    strategies.add((T) strategy);
                }
                //..........................................略
            }
            return strategies;
        }
        else {
            return Collections.emptyList();
        }
    }
```

```java
/*********************************** RequestMappingHandlerMapping.java *******************************/
    public void afterPropertiesSet() {
        //..........................................略
		super.afterPropertiesSet();
	}
    protected boolean isHandler(Class<?> beanType) {
        // 判断该类型的类是否添加 @Controller 注解
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
    }
    
/*********************************** AbstractHandlerMethodMapping.java *******************************/
    public void afterPropertiesSet() {
		initHandlerMethods();
	}
    protected void initHandlerMethods() {
        for (String beanName : getCandidateBeanNames()) {
            if (!beanName.startsWith(SCOPED_TARGET_NAME_PREFIX)) {
                processCandidateBean(beanName);
            }
        }
        handlerMethodsInitialized(getHandlerMethods());
    }
    protected void processCandidateBean(String beanName) {
        //..........................................略
        
        if (beanType != null && isHandler(beanType)) {
            detectHandlerMethods(beanName);
        }
    }
    protected abstract boolean isHandler(Class<?> beanType);
```