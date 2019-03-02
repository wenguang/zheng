 

注解就相当于**标签**，它可贴在类、方法、属性上面，这些类或被注解标注了，就说明类或方法被看作是有了某种特性。

**但它的注解本身不会执行功能，而是编译器或运行器或自定义的代码识别了这个注解而执行某个功能，这时注解才真正有了其代表的特性的功能。**

@BaseService真正起作用的地方在ApplicationContextListener的onApplicationEvent事件响应方法中

```java
@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // root application context
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            LOGGER.debug(">>>>> spring初始化完毕 <<<<<");
            // spring初始化完毕后，通过反射调用所有使用BaseService注解的initMapper方法
            Map<String, Object> baseServices = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for(Object service : baseServices.values()) {
                LOGGER.debug(">>>>> {}.initMapper()", service.getClass().getName());
                try {
                    Method initMapper = service.getClass().getMethod("initMapper");
                    initMapper.invoke(service);
                } catch (Exception e) {
                    LOGGER.error("初始化BaseService的initMapper方法异常", e);
                    e.printStackTrace();
                }
            }
```





元注解：用于标注注解本身的，有5个，@Retention、@Documented、@Target、@Inherited、@Repeatable 



@Target：表示注解可被作用在类上、方法...

- ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
- ElementType.CONSTRUCTOR 可以给构造方法进行注解
- ElementType.FIELD 可以给属性进行注解
- ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
- ElementType.METHOD 可以给方法进行注解
- ElementType.PARAMETER 可以给一个方法内的参数进行注解
- ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举



@Retention：表示注解起作用的阶段，编译阶段或运行时或...

- RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
- RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
- RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。



详细可参考：

最易懂的注解说明：[秒懂，Java 注解 （Annotation）你可以这样学](https://blog.csdn.net/briblue/article/details/73824058)