1,网关配置文件要加配置
  - id: payment_route3_socket
  #具体路由到哪个服务
  uri: lb:ws://cloud-payment-service  #lb:ws://xxx 表示从注册中心获取路径转发，并且请求协议换成ws
  predicates:
   #注解@ServerEndpoint(value = "/smartPhoneWs/{userId}/{businessId}")
    - Path=/smartPhoneWs/**
2,网关拦截器
    详情可以看类WebSocketFilter
3,websocket具体实现类SmartPhoneWebSocket