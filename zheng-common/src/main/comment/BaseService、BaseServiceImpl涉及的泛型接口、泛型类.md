泛型的设计是为类型安全的考虑，只对给定的类型及其子类做处理。减少不必要的类型转换，避免出现ClassCastException异常，编译器没法帮忙做类型检查。




来看看这四段代码

BaseService
```java
public interface BaseService<Record, Example> {...}
```

BaseServiceImpl
```java
/**
虽然BaseServiceImpl实现了泛型接口BaseService，但它却不给BaseService声明的泛型指定类型，我想因为它本身是一个泛型类。当一个泛型类实现了一个泛型接口的时，它就自动获取了接口中声明的泛型，它还可以增加声明新的泛型，这里BaseServiceImpl就新声明了Mapper
*/
public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {...}
```

UpmsLogService
```java
/**
这里的UpmsLog和UpmsLogExample要是换成泛型就会的报错,当一个泛型接口A要被接口B继承时，就得给接口所声明的泛型指定类型。
当一个接口继承一个泛型接口，那它(UpmsLogService)算不算是一个泛型接口呢？
若UpmsLogService要声明一个新泛型又该如何做呢？
*/
public interface UpmsLogService extends BaseService<UpmsLog, UpmsLogExample> {...}
```

UpmsLogServiceImpl
```java
/**
当一个非泛型类(UpmsLogServiceImpl)要实现继承一个泛型类(BaseServiceImpl)时，要给泛型类声明的泛型指定类型(如UpmsLogMapper、UpmsLog、UpmsLogExample)
*/
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {...}
```



**从这四段代码中看出要注意：以上代码的尖括号中有些是泛型声明，有些是类型，什么时候有用泛型声明，什么时候用明确的类型，就值得注意了。这是关乎泛型的继承问题，请参考：[泛型继承的几种写法](https://blog.csdn.net/ShierJun/article/details/51253870)**



参考：

[泛型的意义和作用是啥？](https://www.jianshu.com/p/5179ede4c4cf)

[泛型继承的几种写法](https://blog.csdn.net/ShierJun/article/details/51253870)

