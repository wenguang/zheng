mybatis-generator自动生成很多的sql，但这些sql没有分页的功能，又不好直接修改这些动态sql，那最好就是用分页插件，**它的原理就是在sql执行前进行拦截，加上分页语句，最终执行分页查询。** 



怎样使用pagehelper插件参考官方文档：[如何使用分页插件](https://pagehelper.github.io/docs/howtouse/) 



1、在mybatis-config.xml配置pagehelper

2、在applicationContext-jdbc.xml中给SqlSessionFactoryBean工厂指定mybatis-config.xml的位置

```xml
<!-- 为Mybatis创建SqlSessionFactory，同时指定数据源 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath*:com/zheng/upms/dao/mapper/*Mapper.xml"/>
</bean>
```

3、在BaseServiceImpl中设置分页参数

```java
//两种设置参数的方式
PageHelper.startPage(pageNum, pageSize, false);
PageHelper.offsetPage(offset, limit, false);
```



**因为pagehelper是非侵入性的，所以不用修改Mapper接口，只需要在调用Mapper接口方法之前用以上两种语句之一设置了分页参数，pagehelper只会在sql执行前的某一时刻拦截，在原先的Mapper接口方法所映射的sql后加上分页语句，这样就完成了分页功能** 