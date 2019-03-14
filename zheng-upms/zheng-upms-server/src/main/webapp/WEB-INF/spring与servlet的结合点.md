####ContextLoaderListener继承自ContextLoader，且实现ServletContextListenerspring技术是基于servlet的，要与servlet整合，那一般都实现ServletContextListener，让spring接入servlet的上下文，contextConfigLocation就定义在ContextLoader中。

```
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>

<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

