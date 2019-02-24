
@ExceptionHandler是springMVC的一个注解，它用来标注一个方法，可对web服务端错误作统一处理，

**但有个前提：实现@ExceptionHandler标注方法的类被那些Controller类继承，那些Controller中的错误才会被该方法处理。**


在这里就是继承了BaseController类的那些Controller（Controller是处理http请求的）若出现了错误了，那请求就会被传给BaseController的exceptionHandler方法来处理。




参考：[Spring MVC @ExceptionHandler的使用](https://blog.csdn.net/lsm135/article/details/52625483)