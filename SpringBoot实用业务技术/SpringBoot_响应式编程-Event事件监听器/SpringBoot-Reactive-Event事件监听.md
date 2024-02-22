
## 一、Reactive 
    Reactive 直接翻译的意思式反应式，反应性。

    举个例子：在 Excel 里，C 单元格上设置函数 Sum(A+B)，当你改变单元格 A 或者单元格 B 的数值时，
            单元格 C 的值同时也会发生变化。这种行为就是 Reactive。

    在计算机编程领域，Reactive 一般指的是 Reactive programming。
    指的是一种面向数据流并传播事件的异步编程范式（asynchronous programming paradigm）。


## 二、Reactive Streams 规范
   - ### Publisher（数据发布者）
     ```
     public interface Publisher<T> {    
        // 通过此方法将数据发布出去
        public void subscribe(Subscriber<? super T> s);
     }
     ```

   - ### Subscriber（数据订阅者）
     ```
     publicinterface Subscriber<T> {
        // 订阅成功的回调方法，用于初始化Subscription，并且表明开始接受订阅数据了
        public void onSubscribe(Subscription s);
     
        // 接收下一项订阅数据的回调方法
        public void onNext(T t);
     
        // 在Publisher和Subscriber遇到不可恢复的错误时调用此方法，Subscriber不再接收订阅信息
        public void onError(Throwable t);
     
        // 当接收完所有的订阅数据，并且发布者已经关闭后会回调此方法
        public void onComplete();
     }
     ```

   - ### Subscription（发布者和订阅者之间的订阅关系）
     ```
     public interface Subscription { 
        // 用于向数据发布者请求n个数据项 
        public void request(long n); 
      
        // 取消消息订阅，订阅者将不再接收数据
        public void cancel();
     }
     ```

- ### Processor（数据处理器）
     ```
     public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
        它是一个空接口，但是它继承了Publisher和Subscriber，所以它既能发布数据也能订阅数据。
        基于这个特性，它可以充当数据转换的角色，先从数据发布者那接收数据项，然后经过处理后再发布给最终的数据订阅者。
     }
     ```
- ### Reactive Streams 规范强调使用非阻塞异步方式而不是阻塞同步方式?
   - 同步方式一般通过多线程来提高性能，但系统可创建的线程数是有限的，且线程多以后造成线程切换开销。  
   - 同步方式很难进一步提升资源利用率。  
   - 同步调用依赖的系统出现问题时，自身稳定性也会受到影响

- ### 背压（Backpressure）
     &emsp;&emsp;&emsp;所谓的背压（Backpressure）通俗的讲就是数据接收者的压力，传统模式下，发布者只关心数据的创造与发布，
     而当数据发布速率远高于数据接收速率的时候，数据接收者缓冲区将被填满，无法再接收数据。
     发布者并不关心这些，依旧不断地发送数据，所以就造成了IO阻塞。基于响应式模型实现的Flow API可以很好地解决这个问题。
     在Java 9的Flow API定义中，Subscriber会将Publisher发布的数据缓冲在Subscription中，其长度默认为256。  

     &emsp;&emsp;&emsp;假如当这个缓冲区都被填满后，Publisher将会停止发送数据，直到Subscriber接收了数据Subscription有空闲位置的时候，Publisher才会继续发布数据，而非一味地发个不停。

## 三、JDK的Reactive Streams 规范的实现（Java 9 Flow）
   - 发布-订阅者模式（观察者模式）  
       Publisher发布者发布 一个或多个Subscriber订阅者消费,每个订阅者被Subscription管理的流式控制组件



## 四、Spring的Reactive Streams 规范的实现（Reactor 框架）
   











一、Spring的事件机制是Spring框架中的一个重要特性，基于观察者模式实现，它可以实现应用程序中的解耦，提高代码的可维护性和可扩展性。
    
    Spring的事件机制包括事件、事件发布、事件监听器等几个基本概念。
       其中，事件是一个抽象的概念，它代表着应用程序中的某个动作或状态的发生。
       事件发布是事件发生的地方，它负责产生事件并通知事件监听器。
       事件监听器是事件的接收者，它负责处理事件并执行相应的操作。
       在Spring的事件机制中，事件源和事件监听器之间通过事件进行通信，从而实现了模块之间的解耦。

    举个例子：用户修改密码，修改完密码后需要短信通知用户，记录关键性日志，等等其他业务操作。


 2. 观察者模式角色
   Subject：抽象主题（抽象被观察者），抽象主题角色把所有观察者对象保存在一个集合里，每个主题都可以有任意数量的观察者，抽象主题提供一个接口，可以增加和删除观察者对象。
   ConcreteSubject：具体主题（具体被观察者），该角色将有关状态存入具体观察者对象，在具体主题的内部状态发生改变时，给所有注册过的观察者发送通知。
   Observer：抽象观察者，是观察者者的抽象类，它定义了一个更新接口，使得在得到主题更改通知时更新自己。
   ConcrereObserver：具体观察者，实现抽象观察者定义的更新接口，以便在得到主题更改通知时更新自身的状态。

 3. 事件机制实现方式
   实现Spring事件机制主要有4个类：

   ApplicationEvent：事件，每个实现类表示一类事件，可携带数据。
   ApplicationListener：事件监听器，用于接收事件处理时间。
   ApplicationEventMulticaster：事件管理者，用于事件监听器的注册和事件的广播。
   ApplicationEventPublisher：事件发布者，委托ApplicationEventMulticaster完成事件发布。