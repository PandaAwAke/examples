# 微服务样例程序

- scripts 文件夹下包含了运行环境，直接 docker compose 运行即可。
  - localhost:18080 为 nginx 前端
  - localhost:3306 为 mysql
  - localhost:8848 为 nacos
  - localhost:9000 为 sentinel-dashboard

- 微服务
  - localhost:8080 为 Gateway
  - localhost:8090 为 ItemService
  - localhost:8100 为 CartService
  - localhost:8110 为 PayService
  - localhost:8120 为 TradeService
  - localhost:8130 为 UserService
