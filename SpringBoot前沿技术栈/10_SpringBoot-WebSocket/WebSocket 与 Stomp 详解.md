
## Spring Boot 集成 WebSocket 的两种主要方式
## 一、原生 @ServerEndpoint 
## 二、Spring STOMP 的@MessageMapping

### 以上两种方式有显著区别，主要体现在协议支持、使用场景和集成复杂度上。以下是详细对比：

1. 协议层面对比

   | 特性     | @ServerEndpoint (原生 WebSocket) | @MessageMapping (STOMP over WebSocket)   |
   |:--------|    :----:                        |              ---:                        |
   | 协议支持 | 原生 WebSocket 协议（底层 TCP）     | STOMP 协议（基于 WebSocket 的更高层消息协议）|
   | 消息格式 | 自定义格式（通常为字符串或二进制）     | 支持 JSON、XML 等结构化格式（依赖 STOMP 头） |
   | 订阅模式 | 需手动实现广播、点对点               | 内置广播、点对点（通过 /topic、/queue）      |

2. 功能与复杂度对比

   | 维度      |     @ServerEndpoint (原生 WebSocket)     | @MessageMapping (STOMP over WebSocket) |
   |:--------  | :---------------------------: | -------------:|
   | 连接管理   |   需手动维护 CopyOnWriteArraySet<Session>  | 由 Spring 自动管理 |
   | 消息路由   |   需自行实现广播、定向发送逻辑                | 通过 @SendTo 或 SimpMessagingTemplate 自动路由 |
   | 拦截器/鉴权 |  需自定义 ServerEndpointConfig.Configurator | 可直接用 Spring Security 进行鉴权 |
   | 适用场景   |   需要精细控制底层协议（如二进制流）            | 快速实现结构化消息（如聊天室、通知系统） |

总结
   原生 WebSocket：更底层，灵活但复杂，适合协议定制化场景。
   STOMP over WebSocket：更高层，开箱即用，适合大多数实时消息交互（如聊天、通知）。
