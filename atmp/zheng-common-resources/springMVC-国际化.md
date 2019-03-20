配置**ReloadableResourceBundleMessageSource**告诉spring上下文国际化资源的位置

```xml
<!-- i18n国际化 -->
<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
    <!-- 找不到key时用key作为返回值 -->
    <property name="useCodeAsDefaultMessage" value="false"/>
    <!-- 资源刷新时间 -->
    <property name="cacheSeconds" value="60"/>
    <!-- 资源文件列表 -->
    <property name="basenames">
        <list>
            <value>classpath:i18n/messages</value>
        </list>
    </property>
    <property name="defaultEncoding" value="UTF-8"/>
</bean>
```

用户的浏览器设置为什么语言，可以accept-language获取到，从而输出相应语言的资源即可，**读取accept-language的方式是默认，springmvc也默认提供了AcceptHeaderLocaleResovler区域解析器，所以只必须配置国际化资源的位置即可。**

此外，还可根据session和cookie获取用户的语言区域，这两种情况比较少用。