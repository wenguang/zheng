它有构造函数，和一般的函数一样，要注意是枚举值的写法
```java
public enum DataSourceEnum {

	// 主库
	MASTER("masterDataSource", true),
	// 从库
	SLAVE("slaveDataSource", false),;
    
	
}
```


枚举值有点相当于类的预定对象


枚举还可以定义方法
```java
public enum DataSourceEnum {
    
    public String getDefault() {
    		String defaultDataSource = "";
    		for (DataSourceEnum dataSourceEnum : DataSourceEnum.values()) {
    			if (!"".equals(defaultDataSource)) {
    				break;
    			}
    			if (dataSourceEnum.master) {
    				defaultDataSource = dataSourceEnum.getName();
    			}
    		}
    		return defaultDataSource;
    	}
}
```
这样调用它
```java
DataSourceEnum.MASTER.getDefault()
```