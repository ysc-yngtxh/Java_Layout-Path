
## 一、Reactive 
    Reactive 直接翻译的意思式反应式，反应性。

    举个例子：在 Excel 里，C 单元格上设置函数 Sum(A+B)，当你改变单元格 A 或者单元格 B 的数值时，
            单元格 C 的值同时也会发生变化。这种行为就是 Reactive。

    在计算机编程领域，Reactive 一般指的是 Reactive programming。
    指的是一种面向数据流并传播事件的异步编程范式（asynchronous programming paradigm）。


## 二、Reactive Streams 规范
   - ### Publisher（定义了发布者的方法）
     ```
     public interface Publisher<T> {    
        public void subscribe(Subscriber<? super T> s);
     }
     ```

   - ### Subscriber（定义了订阅者的方法）
     ```
     publicinterface Subscriber<T> {
        public void onSubscribe(Subscription s);
        public void onNext(T t);
        public void onError(Throwable t);
        public void onComplete();
     }
     ```

   - ### Subscription（定义了连接发布者和订阅者的方法）
     ```
     public interface Subscription {  
        public void request(long n);  
        public void cancel();
     }
     ```

- ### Processor（定义了处理器）
     ```
     public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
     
     }
     ```













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