# rocketMQ-util

基于阿里[RocketMQ](https://github.com/alibaba/RocketMQ)搭建.
本项目为示例项目与工具包项目的结合.
由于官方文档中的示例方法存在问题(比如必填参数没有注明),故重新进行整合优化.:P

### 使用方法

- 首先,用你的支付宝帐号登录阿里云,并开通消息服务中间件.
- 其次,由于目前在公测阶段,请绑定host: 140.205.155.65 ons.aliyuncs.com
- 下载此jar包,尽量使用com.linzi.commons.linzimq.ons里的方法,这个是基于阿里云消息中间件服务的.
- 其余请参考javadoc.

### 注意事项

此项目不断随着阿里的更新而更新.

如果要将此项目发布到您的nexus私服.(当然,如果要发布到公有服,在[mvn-push.gradle](mvn-push.gradle)里加入签名等各种方法即可.)

  - gradle方式脚本已写好，请修改`gradle.properties.sample`为`gradle.properties`文件，并写入个人信息。例如：

  ```
  systemProp.username = 您的nexus私服用户名
  systemProp.password = 您的nexus私服用户密码
  systemProp.release = 您的nexus私服发布库地址
  systemProp.snapshot = 您的nexus私服快照库地址
  ```

  - maven方式，那就请你自己写吧。

### [版本信息](VERSION_HISTORY.md)

