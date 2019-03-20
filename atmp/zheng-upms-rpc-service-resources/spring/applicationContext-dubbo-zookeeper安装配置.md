**dubbo把服务注册到zookeeper中** 



**zookeeper是什么东东？** 

只能简单地引用说下zookeeper是什么东东？是的，只能简单说下，因为它还算复杂的东西，对我来说了。

```
ZooKeeper是一个分布式的，开放源码的分布式应用程序协调服务，是Google的Chubby一个开源的实现，它是集群的管理者，监视着集群中各个节点的状态根据节点提交的反馈进行下一步合理操作。最终，将简单易用的接口和性能高效、功能稳定的系统提供给用户
```

详细参考：[Zookeeper的功能以及工作原理](https://www.cnblogs.com/felixzh/p/5869212.html) 



从这里http://zookeeper.apache.org/doc/current/zookeeperStarted.html 下载后解压到一个目录下就可以（如/usr/local/zookeeper-3.4.8 ）

配置文件：conf/zoo.cfg

```
#zookeeper结点的检测心跳时间，这里是2秒
tickTime=2000
#内存数据库快照位置
dataDir=/var/lib/zookeeper
#监听服务连接的端口，连接后服务成为zookeeper的结点
clientPort=2181
```

启动zookeeper

```shell
sudo bin/zkServer.sh start
```

zookeeper以结点方式连接到server

```shell
bin/zkCli.sh -server 127.0.0.1:2181
```



**dubbo又是什么东东？**





dubbo的xml方式配置（）

```xml
    <!-- 提供方应用信息，用于计算依赖关系 -->
	<dubbo:application name="zheng-upms-rpc-service"/>
	<!-- 使用zookeeper注册中心暴露服务地址 -->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
	<!-- 用dubbo协议在20881端口暴露服务 这个端口可用于点对点调试-->
    <dubbo:protocol name="dubbo" port="20881"/>

    <!-- 和本地bean一样实现服务 -->
    <bean id="upmsSystemService" class="com.zheng.upms.service.UpmsSystemServiceImpl"/>
	<!-- 声明需要暴露的服务接口 -->
    <dubbo:service interface="com.zheng.upms.service.UpmsSystemService" ref="upmsSystemService" timeout="10000"/>
```

