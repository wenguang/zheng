**springMVC-servlet.xml中有这样三个配置：**

\<context:annotation-config/\>

\<context:component-scan back-package="…"/\>

\<mvc:annotation-driven\>…\</mvc:annotation-driven\>

它们有什么作用呢？在说它们之前，先看看几个注解：

- @Service("xxx")：它标在类上面，用来向springIOC容器注册该类的bean，bean名称就是xxx，若只用@Service，那bean就是类名首字母小写。
- @Resource(name="xxx")、@Resource(type=xxx)、@Resource：它标在属性上，为该属性注册一个在IOC容器中存在的名称为xxx或类型为xxx的bean，只用@Resource则按名称注册
- @Autowired：标在属性上，自动注入一具在IOC容器中存在的该属性类型的bean，它和@Resource很像，只是@Resource有byName和byType的选择。
- @Required：标在属性上，表明该属性必须设置，但不检查非空的情况。

注解都有相应的的处理器来处理，若想上面的注解生效，就得向IOC容器注册相应的处理器。



**\<context:annotation-config/\>**就隐性地注册4个处理器：

AutowiredAnnotationBeanPostProcessor、RequiredAnnotationBeanPostProcessor、CommonAnnotationBeanPostProcessor、PersistenceAnnotationBeanPostProcessor（JPA用到）。

\<context:annotation-config/\>除了让以前注解生效，还让以下3个注解生效，但比较少用到

@PostConstruct（影响到servlet的生命周期）

@ProDestroy（影响到servlet的生命周期）

@PersistenceContext（JPA用到）



**\<context:component-scan\>**做了\<context:annotation-config/\>的事情，还支持@Service、@Component、@Repository、@Controller、@Configuration、@RestController、@ControllerAdvice注解，**且扫描back-package下标志了这些注解的类，把它们注册为IOC容器的bean。** 



**\<mvc:annotation-driven\>**是mvc的配置，支持用注解的方式开发mvc，**AnnotationDrivenBeanDefinitionParser**是这个配置的解析类，从它的注释中，看出它向IOC容器注册了以下东西

**请求映射**

RequestMappingHandlerMapping（使@RequestMapping注解生效）

BeanNameUrlHandlerMapping（将controller类的名字映射为请求url）

**处理请求**

RequestMappingHandlerAdapter（处理@Controller注解的处理器，支持自定义方法参数和返回值）

HttpRequestHandlerAdapter（处理继承HttpRequestHandler的处理器）

SimpleControllerHandlerAdapter（处理继承自Controller接口的处理器）

**异常解析**

ExceptionHandlerExceptionResolver（使@ExceptionHandler生效）

ResponseStatusExceptionResolver

DefaultHandlerExceptionResolver

**另外还将提供以下支持：**

① 支持使用ConversionService实例对表单参数进行类型转换；
② 支持使用@NumberFormatannotation、@DateTimeFormat注解完成数据类型的格式化；
③ 支持使用@Valid注解对Java bean实例进行JSR 303验证；

④ 支持使用@RequestBody和@ResponseBody注解

