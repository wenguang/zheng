ApplicationContextListenter实现了spring的泛型接口ApplicationListener\<E extends ApplicationEvent\>，这个接口只有一个方法：onApplicationEvent，它监听spring的生命周期，以便完成需要的处理，在zheng项目就是要在spring初始化完成后，对xxxServiceImpl中的Mapper的初始化。

**spring初始化完成（容器加载完成）判断**

```java
if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
    //...
}
```

**获取由spring管理的所有xxxServiceImpl（在xxx-rpc-service中）实例**

```java
// spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
Map<String, Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);

```

