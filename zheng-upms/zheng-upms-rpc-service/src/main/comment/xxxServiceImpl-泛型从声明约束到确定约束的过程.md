**从误解开始** 

```java
// 1
public interface BaseService<Record, Example> 
// 2
public interface UpmsRolePermissionService extends BaseService<UpmsRolePermission, UpmsRolePermissionExample>
// 3
public abstract class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example>
// 4
public class UpmsRolePermissionServiceImpl extends BaseServiceImpl<UpmsRolePermissionMapper, UpmsRolePermission, UpmsRolePermissionExample> implements UpmsRolePermissionService
```

原先看到<>中间的Mapper、Record、UpmsRolePermission等这些**“变量”**时，我想的是怎么传值给这些**“变量”**的呢？**思考传值的话，思路就错了！** 

**可以把泛型看作是约束，声明一个泛型就是声明一个约束，当这个泛型被指定明确的类型后，就是明确了这个约束的范围，泛型可以约束类的属性、方法的参数或返回值，接口方法的参数或返回值** 

