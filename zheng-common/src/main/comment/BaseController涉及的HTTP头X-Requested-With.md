以X开头的HTTP头部是非HTTP的标准协议

若HTTP头部中有X-Requested-With且值为XMLHTTPRequest就表示该请求是ajax请求

```java
if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
    request.setAttribute("requestHeader", "ajax");
}
```


参考：[HTTP之X-Requested-With分析和思考](https://blog.csdn.net/javajiawei/article/details/50563154)


