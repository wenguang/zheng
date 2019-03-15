
####\<mvc:interceptors\>

匹配某个路径下的访问，在请求到达该路径的controller前进行拦截
```xml
    <!-- 拦截器 -->
    <mvc:interceptors>
        <!-- 获取登录信息 -->
        <mvc:interceptor>
            <mvc:mapping path="/manage/**"/>
            <bean class="com.zheng.upms.server.interceptor.UpmsInterceptor"></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

上例子配置了一个拦截器，它对/manage/**路径的请求进行拦截，在执行该路径下的controller前，先招待UpmsInterceptor拦截器的逻辑