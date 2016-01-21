# 版本信息
- 0.0.1-SNAPSHOT:
  - 初次提交.
  - 普通消息收发完成.
  - MQTT消息收发完成.

- 0.0.2-SNAPSHOT:
  - 增加阿里ONS API.
  - 完成ONS的Topic,Region管理部分.

- 0.0.3-SNAPSHOT:
  - 增加ONS的Consumption, Message, Publish, Subscribe部分.

- 0.0.4-SNAPSHOT:
  - 修改MQTT默认QOS.

- 0.0.5-SNAPSHOT:
  - 增加ONS的Cluster, Warning.
  - 增加[ContactsParam](src/main/java/com/saintdan/util/rocketmq/param/ContactsParam.java), TODO ONS管理平台中,可创建多个联系人,现在拼接方法不明.可能会有变化.
  - Warning的测试用例没通过,返回"com.aliyuncs.exceptions.ClientException: ONS_INVOKE_ERROR : The url is not available. Please check it again." 看来文档需要补全.
