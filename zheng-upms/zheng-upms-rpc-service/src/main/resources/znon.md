####applicationContext中使用变量${xxx}的两种方法

1、`<context:property-placeholder location="xx.properties"/>` 这个一目了然，就是引用某properties，那自然就可以用properties来的变量。

2、利用maven在pom文件的build结点下配置

```xml
<build>
    <!-- ...省略... -->
	<filters>
        <filter>src/main/resources/xx.properties</filter>
    </filters>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
    <!-- ...省略... -->
</build>
```

意思就是资源目录src/main/resources启用过滤，过滤时其中的变量用src/main/resources/profiles/xx.properties属性文件中的值替换

**看来用maven的方式要复杂，它有更强大作用，就是结点profile可以在不同的环境下执行不同的配置** 







####运行JAR包 提示没有主清单属性



