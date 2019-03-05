以下这个配置一看就很熟悉

```xml
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

这是让spring框架捕获所有的请求并转给相应的处理器做处理，包括静态资源请求，被DispatcherServlet捕获的都被当为一般请求，那静态资源请求很可能找不到相应的处理器。



**springmvc3.0后给出两个不让DispatcherServlet处理静态请求的解决方案：** 

**1、采用<mvc:default-servlet-handler />**

在springmvc上下文定义一个org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler，它对捕获的请求进行检查，若是表态资源请求，则转给web应用服务器（如tomcat、jetty）本身的servlet处理（tomcat、jetty都是实现了servlet标准的）。

**2、采用<mvc:resources />**

```
<mvc:resources location="/,classpath:/META-INF/publicResources/" mapping="/resources/**"/>
```

由springMVC框架来处理静态请求（springmvc3.0增强了静态资源处理的能力），它相对web服务器本身处理静态请求的好处是：

1、允许静态资源放在任何地方，如WEB-INF目录下、类路径下等，你甚至可以将JavaScript等静态文件打到JAR包中。通过location属性指定静态资源的位置，由于location属性是Resources类型，因此可以使用诸如"classpath:"等的资源前缀指定资源位置。传统Web容器的静态资源只能放在Web容器的根路径下，<mvc:resources />完全打破了这个限制。

2、cacheSeconds属性指定静态资源在浏览器端的缓存时间，在输出静态资源时，会根据配置设置好响应报文头的Expires 和 Cache-Control值，在接收到静态资源的获取请求时，会检查请求头的Last-Modified值，如果静态资源没有发生变化，则直接返回303相应状态码，提示客户端使用浏览器缓存的数据，而非将静态资源的内容输出到客户端，以充分节省带宽，提高程序性能。（这一点在我看来，web应用服务器也能做到的）



**最后，在互联网应用中，一般静态资源的访问都到不了应用服务器（如tomcat、jetty）来处理，由nginx处理才是正常的作法。**



