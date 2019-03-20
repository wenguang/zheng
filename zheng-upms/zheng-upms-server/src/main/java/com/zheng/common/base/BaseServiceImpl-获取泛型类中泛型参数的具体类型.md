BaseServiceImpl有这么一个方法：getMapperClass，它的返回类型是Class\<mapper\>

```java
public Class<Mapper> getMapperClass() {
    return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
}
```



getGenericSuperClass()它返回Type（泛型）

- Type是一个接口。
- Type是Java中所有类型的父接口。
- Type包括：raw type(原始类型，对应Class),parameterized types(参数化类型), array types(数组类型), type variables(类型变量) and primitive types(基本类型，对应Class).
- **Type**是**Class**的父接口。
- **Class**是**Type**的子类。



- java.lang.reflect.TypeVariable
- java.lang.reflect.ParameterizedType
- java.lang.reflect.GenericArrayType



ParameterizedType（参数化类型）XXX\<t,k\>，它的getActualTypeArguments()方法返回<>中的泛型参数数组，这里取第一个，就是Mapper接口



测试代码

```java
// 测试代码 - BaseServiceImpl新增方法
	public Class<Mapper> getMapperClass2() {
		System.out.println(getClass());
		System.out.println(getClass().getGenericSuperclass());
		System.out.println(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

// UpmsLogServiceImpl 加main方法
public static void main(String[] args) {
    UpmsLogServiceImpl uli = new UpmsLogServiceImpl();
    uli.getMapperClass2();
}
```

输出是这样的

```shell
class com.zheng.upms.service.UpmsLogServiceImpl
com.zheng.common.base.BaseServiceImpl<com.zheng.upms.dao.mapper.UpmsLogMapper, com.zheng.upms.dao.model.UpmsLog, com.zheng.upms.dao.model.UpmsLogExample>
interface com.zheng.upms.dao.mapper.UpmsLogMapper
```



**疑问：返回提UpmsLogMapper接口类型（非泛型），而方法的返回类型是Class\<Mapper\>（泛型） ？？** 

