要实现动态切换数据源，要从org.springframework.jdbc.datasource.look.AbstractRoutingDataSource(一个抽象类)着手。

其中两个重要的属性：

- targetDataSources，是一个map(key-value)
  - targetDataSources中的key对应DataSourceEnum枚举的值
  - targetDataSources中的value对应com.alibaba.druid.pool.DruidDataSource
- defaultTargetDataSource

在xxx-rpc-service模块中的applicationContext-jdbc.xml中有它的配置

```xml
<!-- 动态数据源 -->
<bean id="dataSource" class="com.zheng.common.db.DynamicDataSource">
     <property name="targetDataSources">
            <map key-type="java.lang.String">
                <!-- 可配置多个数据源 -->
                <entry value-ref="masterDataSource" key="masterDataSource"></entry>
                <entry value-ref="slaveDataSource" key="slaveDataSource"></entry>
            </map>
     </property>
     <property name="defaultTargetDataSource" ref="masterDataSource"></property>
</bean>
```

**DynamicDataSource继承了AbstractRoutingDataSource，最关键的是重载determineCurrentLookupKey方法，确定当前用哪个key(就是用哪个数据源)，外部类不调用determineCurrentLookupKey方法，它由AbstractRoutingDataSource来调用以确定当前的数据源，determineCurrentLookupKey只是简单返回从getDataSource获取到数据源的key，外部类是通过setDataSource来设置数据源的key，从而达到切换数据源的目的。** 



setDataSource都是由BaseServiceImpl类的方法调用的，也就是说切换数据源的工作都是由BaseServiceImpl来完成。

