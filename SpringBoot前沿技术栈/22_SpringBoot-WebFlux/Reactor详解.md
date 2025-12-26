1.1 Spring MVC 和 Spring WebFlux 的区别？
Spring MVC 和 Spring WebFlux 都是Spring框架中用于构建Web应用程序的模块，它们之间的主要区别在于它们处理并发请求的方式和所采用的编程模型。

1、编程模型：

Spring MVC： 采用同步的、阻塞的编程模型。每个请求都会在一个单独的线程中处理，线程会一直阻塞直到请求完成。
Spring WebFlux： 采用异步的、非阻塞的编程模型。它基于Reactive Streams标准，使用反应式编程的理念，可以更有效地处理大量并发请求，减少线程资源的浪费。
2、并发处理：

Spring MVC： 使用Servlet API中的阻塞IO来处理请求，每个请求需要一个独立的线程，如果线程池中的线程用尽，新的请求就会被阻塞。
Spring WebFlux： 使用非阻塞IO，通过少量的线程处理大量的并发请求。这可以提高系统的吞吐量，因为不需要为每个请求分配一个独立的线程。
3、适用场景：

Spring MVC： 适用于传统的同步IO的应用场景，特别是那些对实时性要求不是很高的场景。
Spring WebFlux： 适用于需要处理大量并发请求、对实时性要求高的场景，比如实时通信、实时数据推送等。
总的来说，如果需要处理大量并发请求，并且对实时性有较高要求，可以选择Spring WebFlux

1.2 Spring MVC 和 Spring WebFlux 是否可以同时存在？
答案：可以同时存在。如官方文档所述：

1.3 WebFlux 之 Reactor 框架
在Spring WebFlux中，Reactor是一种基于响应式编程的框架，用于处理异步数据流。Reactor实际上是一个库，它提供了一组用于处理反应式流的API和工具。Spring WebFlux通过Reactor来实现响应式的Web编程模型。

以下是Reactor的一些关键概念：

1、Flux（流）： Flux是Reactor中表示包含零个或多个元素的异步序列的主要类型。它可以发出零到N个元素，并且可以是无限的。在WebFlux中，Flux通常用于表示请求和响应的数据流。

Flux<Integer> numbers = Flux.just(1, 2, 3, 4, 5);

2、Mono（单值）： Mono是Reactor中表示包含零个或一个元素的异步序列的类型。它可以发出零或一个元素，类似于Java 8中的Optional。

Mono<String> value = Mono.just("Hello, Reactor!");

3、Scheduler（调度器）： Reactor提供了调度器来控制异步操作的执行。调度器用于在不同的线程或线程池中执行操作，以避免阻塞。

Scheduler scheduler = Schedulers.parallel();

4、操作符（Operators）： Reactor提供了丰富的操作符，用于在Flux和Mono上执行各种转换和操作。这些操作符包括映射、过滤、合并、错误处理等。

Flux<Integer> doubled = numbers.map(n -> n * 2);
