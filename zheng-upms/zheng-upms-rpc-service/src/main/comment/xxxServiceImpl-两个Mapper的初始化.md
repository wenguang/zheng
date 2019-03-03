**读xxxServiceImpl及它的基类，看出它有两个Mapper对象**

以UpmsUserServiceImpl为例

UpmsUserServiceImpl明确定义了一个UpmsUserMapper类型的变量

```java
@Service
@Transactional
@BaseService
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;
```

它的基类BaseServiceImpl声明了一个泛型属性mapper，在定义UpmsUserServiceImpl类时，明确BaseServiceImp的泛型Mapper为UpmsUserMapper类型，就又加了一个UpmsUserMapper类型的属性

```java
public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {

	public Mapper mapper;
```



**为什么需要两个一样的mapper对象呢？** 

UpmsUserServiceImpl和BaseServiceImpl的代码上看出，大多的功能都是用BaseServiceImpl实现（都是增查改删的功能），BaseServiceImpl中的泛型Mapper只是声明，未明确类型，但用反射技术实现了这些功能。UpmsUserServiceImpl可能要增加BaseServiceImpl中没有的功能，所以定义了明确类型的UpmsUserMapper。



**那UpmsUserServiceImpl可不可以把基类的泛型属性转化为明确类型呢？** 



**这两个mapper的初始化** 

UpmsUserMapper因为用@Autowired注解，所以spring会自动生成该属性。

而基类的mapper的初始化则要复杂一些，要从ApplicationContextListener说起了：

1、ApplicationContextListener在spring容器加载完成后找到所有带有@BaseService注解的类（就是xxxServiceImpl类），调用基类BaseService的initMapper方法。

2、BaseService获取参数化（泛型）类型的第一个参数的类型（就是尖括号内第一个泛型的明确类型）。

3、SpringContextUtil在spring上下文中找到这个明确类型的bean返回给BaseServce。

4、把返回的bean赋值给mapper属性，完成初始化。