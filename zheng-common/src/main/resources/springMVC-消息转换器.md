springMVC-servlet.xml有这么一段

```xml
<!-- 返回ResponseBody响应类型 -->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                    </list>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

**为什么\<mvc:annotation-driven/\>会有一个\<mvc:message-converters\>的子结点，它有什么作用呢？** 

```java
@RequestMapping("/getJson")
@ResponseBody
public Map<String, Object> getJson() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("list", service.list());
    return map;
}
```

**想让以上代码输出json是很简单的，只需在springmvc配置中加一句\<mvc:annotation-driven/\>即行**

 \<mvc:annotation-driven/\>可以让@ResponseBody注解生效，@ResponseBody注解就使用消息转换机制，通过json转换器得到json数据。

![](https://github.com/wenguang/zheng/blob/dvs/zheng-common/src/main/resources/spring-HttpMessageConverter.png?raw=true)

**\<mvc:annotation-driven/\>的解析器是AnnotationDrivenBeanDefinitionParser类**，它的getMessageConverters方法下有这么一段：

```java
messageConverters.add(this.createConverterDefinition(ResourceHttpMessageConverter.class, source));
messageConverters.add(this.createConverterDefinition(SourceHttpMessageConverter.class, source));
messageConverters.add(this.createConverterDefinition(AllEncompassingFormHttpMessageConverter.class, source));
//...其他转换器
```

**当\<mvc:annotation-driven\>没有子结点或register-defaults为true时，就用默认的转换器工作。这里配置StringHttpMessageConverter转换器，且设置supportedMediaTypes为text/html;charset=UTF-8，这样避免了乱码。** 