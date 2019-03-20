
AOP需要的包：
spring-aop（包含在spring-context中）、aopalliance、aspectjweaver、cglib，它们的关系有待研究



**Pointcut（切点）**是面向切面编程中的一个非常重要的概念，此概念由spring框架定义。
Pointcut的唯一作用就是筛选要拦截的目标方法，因此，有很多人会把Pointcut直接理解成——要拦截的方法，其实不然，Pointcut只是一种筛选规则（或者叫过滤器）。
Pointcut由ClassFilter（类过滤器）和MethodMatcher（方法匹配器）两个组件组成。
ClassFilter检查当前筛选规则与目标类是否匹配，MethodMatcher检查当前筛选规则与目标方法是否匹配。
两个组件的共同作用，可以筛选出一个符合既定规则的方法的集合。通过Advisor（通知器）和Advice（通知）和Pointcut（切点）组合起来，就可以把指定的通知应用到指定的方法集合上。


**看了上面这段，有点懵B，那结合代码看用xml配置和注解两种方式如何实现aop编程** 



**xml配置方式**：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

    <bean id="hello" class="lrc.spring.ccb.aop.Hello"/>
    <bean id="helloAspect" class="lrc.spring.ccb.aop.HelloAspect"/>
    <aop:config>
        <!-- pointcut是拦截过滤规则对象，符合expression的过滤规则的就会被拦截 这是Hello类的所有方法-->
        <!-- 这里过滤规则表达式是AspectJ表达式，即AspectJExpressionPointcut，除了它还有别的过滤规则表达式 -->
        <!-- AspectJExpressionPointcut表达式的基本格式如下：-->
        <!-- execution(<方法修饰符>? <方法返回值类型> <包名>.<类名>.<方法名>(<参数类型>) [throws <异常类型>]?) -->
        <!-- 详细的规则参考：http://sishuok.com/forum/posts/list/281.html -->
        <aop:pointcut id="helloPointcut" expression="execution(* lrc.spring.ccb.aop.Hello.*(..))"/>
        <!-- aspect可以理解为一轮拦截，ref表示要引用哪个拦截器 -->
        <aop:aspect ref="helloAspect">
            <!-- pointcut-ref表示要拦截helloPointcut过滤规则筛选出的方法，method表示方法被拦截后执行拦截器的beforeAdvice -->
            <!-- pointcut-ref也可以换为pointcut，直接写出过滤规则即行，那它就是一个匿名的过滤规则对象 -->
            <aop:before pointcut-ref="helloPointcut" method="beforeAdvice"/>
            <aop:around pointcut="execution(* lrc.spring.ccb.aop.Hello.*(..))" method="aroundAdvice"/>
            <aop:after-returning pointcut-ref="helloPointcut" method="afterReturningAdvice" arg-names="retVal" returning="retVal"/>
            <aop:after-throwing pointcut-ref="helloPointcut" method="afterThrowingAdvice" arg-names="ex" throwing="ex"/>
            <aop:after pointcut-ref="helloPointcut" method="afterFinallyAdvice"/>
        </aop:aspect>
    </aop:config>

</beans>
```

Hello.java
```java
package lrc.spring.ccb.aop;

public class Hello {
    public void sayHello() {
        System.out.println("hello, spring aop");
    }
}
```

HelloAspect.java
```java
package lrc.spring.ccb.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class HelloAspect {
    public void beforeAdvice() {
        System.out.println("before advice");
    }

    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around advice before");
        joinPoint.proceed();
        System.out.println("around advice after");
    }
    public void afterReturningAdvice(Object retVal) {
        System.out.println("after returning, retVal = " + retVal);
    }
    public void afterThrowingAdvice(Exception ex) {
        System.out.println("after throwing exception = " + ex);
    }
    public void afterFinallyAdvice() {
        System.out.println("after finally advice");
    }
}
```



**注解方式**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">

    <!-- 这句至关重要，它使得spring支持注解方式进行切面声明 -->
    <aop:aspectj-autoproxy/>

    <bean id="hello" class="lrc.spring.ccb.aop.Hello"/>
    <bean id="annotationHelloAspect" class="lrc.spring.ccb.aop.AnnotationHelloAspect"/>

</beans>
```

AnnotationHelloAspect.java
```java
package lrc.spring.ccb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AnnotationHelloAspect {

    /**
     * 相当schemalAOP中定义了一个为helloPointcut的Pointcut
     */
    @Pointcut(value = "execution(* lrc.spring.ccb.aop.Hello.*(..)) && args(param)", argNames = "param")
    public void helloPointcut(String param) {
    }

    @Before(value = "helloPointcut(param)", argNames = "param")
    public void beforeAdvice(String param) {
        System.out.println("before advice" + param);
    }

    /**
     * value指定一个定义好的pointcut，pointcut则直接写过滤规则表达式
     * 两者同时存在时，pointcut的优先级高
     */
    @AfterReturning(
            value = "helloPointcut(retVal)",
            pointcut = "execution(* lrc.spring.ccb.aop.Hello.*(..))",
            argNames = "retVal",
            returning = "retVal"
    )
    public void afterReturningAdvice(Object retVal) {
        System.out.println("after returning advice, retVal = " + retVal);
    }

    @AfterThrowing(
            value = "execution(* lrc.spring.ccb.aop.Hello.*(..))",
            argNames = "exception",
            throwing = "exception"
    )
    public void afterThrowingAdvice(Exception exception) {
        System.out.println("after throwing advice, exception = " + exception);
    }

    @After(value = "execution(* lrc.spring.ccb.aop.Hello.*(..))")
    public void afterAdvice() {
        System.out.println("after advice");
    }

    @Around(value = "execution(* lrc.spring.ccb.aop.Hello.*(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around advice before");
        joinPoint.proceed();
        System.out.println("around advice after");
    }
}
```


***看完以上的例子，就对AOP有个初步的理解了***






参考：

[spring-aop组件详解——Pointcut切点](https://my.oschina.net/lixin91/blog/686660)

[AOP 之 6.2 AOP的HelloWorld ——跟我学spring3](http://jinnianshilongnian.iteye.com/blog/1418597)

[AOP 之 6.3 基于Schema的AOP ——跟我学spring3](http://jinnianshilongnian.iteye.com/blog/1418598)