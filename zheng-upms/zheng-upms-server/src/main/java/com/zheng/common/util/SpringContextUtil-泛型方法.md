先看SpringContextUtil中的泛型方法

```java
public static <T> T getBean(Class<T> clazz) {
    T t = null;
    Map<String, T> map = context.getBeansOfType(clazz);
    for (Map.Entry<String, T> entry : map.entrySet()) {
        t = entry.getValue();
    }
    return t;
}
```



**重要的是\<T\>，它表示了对返回值T泛型及参数Class\<T\>中T泛型的声明，如果只是方法中有泛型参数或返回值是泛型（泛型是类声明的而非方法声明的），则不是泛型方法** 



几个非泛型方法的例子

```java
public class Generic<T>{     
	private T key;
    
    //我想说的其实是这个，虽然在方法中使用了泛型，但是这并不是一个泛型方法。
    //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
    //所以在这个方法中才可以继续使用 T 这个泛型。
    public T getKey(){
     	return key;
    }
    
    /**
    * 这个方法显然是有问题的，在编译器会给我们提示这样的错误信息"cannot reslove symbol E"
    * 因为在类的声明中并未声明泛型E，所以在使用E做形参和返回值类型时，编译器会无法识别。
    */
    public E setKey(E key){
    	this.key = keu
    }
         
    /**
    * 这个方法是有问题的，编译器会为我们提示错误信息："UnKnown class 'E' "
    * 虽然我们声明了<T>,也表明了这是一个可以处理泛型的类型的泛型方法。
    * 但是只声明了泛型类型T，并未声明泛型类型E，因此编译器并不知道该如何处理E这个类型。
    */
    public <T> T showKeyName(Generic<E> container){
     	//...
    }  
    
    /** 
    * 这才是一个真正的泛型方法。
    * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
    * 这个T可以出现在这个泛型方法的任意位置.
    * 泛型的数量也可以为任意多个 
    */
    public <T,K> K showKeyName(Generic<T> container){
    	//...
    }
      
    
}
```





参考：[java 泛型详解-绝对是对泛型方法讲解最详细的，没有之一](https://www.cnblogs.com/fnlingnzb-learner/p/7265104.html) 