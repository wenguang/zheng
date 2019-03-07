
org.mybatis.spring.mapper.MapperScannerConfigurer
org.mybatis.spring.SqlSessionFactoryBean
org.springframework.jdbc.datasource.DataSourceTransactionManager

com.alibaba.druid.pool.DruidDataSource
com.zheng.common.db.DynamicDataSource
com.zheng.common.plugin.EncryptPropertyPlaceholderConfigurer



mybatis重要的配置结点两个：SqlSessionFactoryBean和MapperScannerConfigurer

```xml
<!-- 为Mybatis创建SqlSessionFactory，同时指定数据源 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath*:com/zheng/upms/dao/mapper/*Mapper.xml"/>
</bean>
<!-- Mapper接口所在包名，Spring会自动查找其下的Mapper -->
<bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="**.mapper"/>
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
</bean>
```

