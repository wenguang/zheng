*ThreadLocal保证每个线程对变量的专属副本访问，不受其他线程影响，又不需要加锁*

```java
//定义ThreadLocal(泛型)
private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();
//...

//用get set remove操作
//在必要时用remove可避免内存泄漏
```



**想想，为什么这里(DynamicDataSource)数据源的动态切换需要用到ThreadLocal？** 



ThreadLoad用法、原理、内存泄漏问题请参考：[深度揭秘ThreadLocal](https://zhuanlan.zhihu.com/p/34494674)



