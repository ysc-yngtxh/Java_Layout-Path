package com.example;

public class 杂文笔记 {}
/*
一、分布式锁
     1、数据库的排它锁
     2、使用redis分布式锁，通过代码调用setnx命令：redisTemplate.opsForValue().setIfAbsent(); 看门狗机制
     3、使用redission实现分布式锁
     4、使用zookeeper实现分布式锁

二、单例模式
     在Spring中，bean可以被定义为两种模式：prototype（多例）和singleton（单例）
         singleton（单例）：只有一个共享的实例存在，所有对这个bean的请求都会返回这个唯一的实例。
         prototype（多例）：对这个bean的每次请求都会创建一个新的bean实例，类似于new。
         Spring bean 默认是单例模式。

     单例有三种方式：饿汉式单例，懒汉式单例，单例注册表

三、在@Configuration类下的@Bean方法何时被调用？
     项目启动的时候，每一个带@Bean注解的方法都被调用了。

四、Restful风格
      每一个资源都拥有一个资源标识，每一个资源的资源标识可以用来唯一的标明该资源
      REST 是围绕资源为核心的，任何接口的设计都是围绕着资源进行，而不再是围绕着业务功能

      资源操作动作
      不使用动词来描述要对资源进行的操作，那么REST中该如何表示CURD呢？
      一般我们会借助于HTTP协议中的请求方法来表明对资源的操作

      以前的URL:
              查询用户：http://localhost:8080/user/query?id=1    -RequestMapping
              添加用户：http://localhost:8080/user/insert        -RequestMapping
              修改用户：http://localhost:8080/user/update        -RequestMapping
              删除用户：http://localhost:8080/user/delete?id=1   -RequestMapping
      如今的URL:
              查询用户：http://localhost:8080/user/{id}     -GETMapping
              添加用户：http://localhost:8080/user/         -POSTMapping
              修改用户：http://localhost:8080/user/         -PUTMapping
              删除用户：http://localhost:8080/user/{id}     -DELETEMapping

      @ResponseBody与ResponseEntity:
           @ResponseBody:是返回一个json的响应体
           ResponseEntity：是返回一个响应实体，是带有状态码的。

      例如：@GetMapping("/{id}")
           public ResponseEntity<Brand> queryBrandById(@PathVariable("id") Long id){
               return ResponseEntity.ok(brandService.queryById(id));
           }
           @PostMapping
           public ResponseEntity<Void> saveBrand(Brand brand,@RequestParam("cids") List<Long> cids){
               //这里的新增商品不需要返回值，所以我们泛型里写上Void.
               brandService.saveBrand(brand,cids);
               return ResponseEntity.status(HttpStatus.CREATED).body("新增商品失败！");
               return ResponseEntity.status(HttpStatus.CREATED).build();因为我们不需要返回值，所以可以不去写body，写上build会更合适
           }

           
      Arrays.asList()方法可以将数组转化为集合，也可以往里面添加数据。
            stream().map(Category::getName).collect(Collectors.toList())
            首先是在流Stream()中创建一个管道，取出Category中的所有getName,然后到collect的List集合中进行遍历。

            stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组，Java容器或I/O channel等。
           对stream的任何修改都不会修改背后的数据源，比如对stream执行过滤操作并不会删除被过滤的元素，
           而是会产生一个不包含被过滤元素的新stream。
           stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，想要再次遍历必须重新生成。
六、分页查询
    MySQL数据库中的分页查询的page第一页的值和ElasticSearch搜索引擎中的分页查询的page第一页的值为0.

    1.m代表从m+1条记录行开始检索，n代表取出n条数据。(m可设为0)
      如：SELECT * FROM 表名 limit 6,5;
      表示：从第7条记录行开始算，取出5条数据
    2.值得注意的是，n可以被设置为-1，当n为-1时，表示从m+1行开始检索，直到取出最后一条数据。
      如：SELECT * FROM 表名 limit 6,-1;
      表示：取出第6条记录行以后的所有数据。
    3.若只给出m，则表示从第1条记录行开始算一共取出m条
      如：SELECT * FROM 表名 limit 6;
      表示：取出前6条记录行。

*/
