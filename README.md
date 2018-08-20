# spring-example
### spring搭建微服务示例

工程名 | 服务名称 | 作用
---|--- | ---
microservice-comm | 服务通用工具包 | 存储服务间通用的常量
eureka-example | 服务注册中心 | 用于注册/下发各服务的节点地址信息
microservice-gateway-example | 服务网关 | 用于对请求路由到对应服务，以及访问权限控制
microservice-consumer-example | 服务消费者 | 消费者角色用来消费请其他服务提供者的服务，通过@LoadBalanced可以对服务提供者的多个节点进行负载
microservice-provider-example | 服务提供者 | 提供服务给其他消费者角色消费
