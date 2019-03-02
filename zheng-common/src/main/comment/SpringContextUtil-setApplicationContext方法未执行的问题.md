SpringContextUtil实现了ApplicationContextAware接口，这个接口只有一个方法setApplicationContext，实现它可能让SpringContextUtil可以持有spring的context，从而可以获取到spring context中的资源，如bean。**但搜索了下setApplicationContext方法，发现zheng的代码中没有什么地方调用到它，那是如何让SpringContextUtil持有spring的context的呢**

```java
//注意是类成员
private static ApplicationContext context = null;
@Override
public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
}
```

**原来setApplicationContext是被spring自动调用的，当然只实现以上代码spring是不会自动调用setApplicationContext的，还得在spring的配置文件中注册一下SpringContextUtil** 

common模块下的applicationContext.xml中（这个文件放着各个模块共用的spring配置）

```xml
<!-- 启动时初始化Spring上下文环境工具类 -->
<bean id="springContextUtil" class="com.zheng.common.util.SpringContextUtil"></bean>
```





参考：[Spring 中 ApplicationContextAware使用理解](https://www.jianshu.com/p/4145f507f3e7)