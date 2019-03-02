mybatis的配置文件可以配置的东西有很多，如下（其实各项配置的含义参考：[详解MyBatis的配置文件](https://www.jianshu.com/p/239e05eeb252)）

```xml
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--配置-->
<configuration>
    <!--属性-->
    <properties></properties>
    <!--设置-->
    <settings>
        <setting name="" value=""/>
    </settings>
    <!--类型命名-->
    <typeAliases></typeAliases>
    <!--类型处理器-->
    <typeHandlers></typeHandlers>
    <!--对象工厂-->
    <objectFactory type=""/>
    <!--插件-->
    <plugins>
        <plugin interceptor=""></plugin>
    </plugins>
    <!--配置环境-->
    <environments default="">
        <!--环境变量-->
        <environment id="">
            <!--事务管理器-->
            <transactionManager type=""></transactionManager>
            <!--数据源-->
            <dataSource type="">
            </dataSource>
        </environment>
    </environments>
    <!--数据库厂商标识-->
    <databaseIdProvider type=""/>
    <!--映射器-->
    <mappers></mappers>
</configuration>
```

**但mybatis-config.xml中除分页插件的配置，就没有再多了，是因为有很多配置被移到xxx-rpc-service下的applicationContext-jdbc.xml中了，mybatis-config.xml是很多模块的共用的配置，而可独立部署的xxx-rpc-service模块对数据访问的配置可能有不同的要求，如配置不同的数据源及切换、指定的不同的Mapper映射、不同的事务管理方式等等（尽管在zheng中各xxx-rpc-service模块的applicationContext-jdbc.xml基本相同），所以mybatis-config.xml只放了一些共性的配置，如分页。** 