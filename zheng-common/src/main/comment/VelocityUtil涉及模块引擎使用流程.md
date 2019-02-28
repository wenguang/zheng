模块的使用说白了就是读取模块文件，把变量值塞到模块中的变量中，输出一个新文件

模块文件（.vm文件）用 ${xxx}（xxx为变量名）声明变量

- Velocity引擎读取模块文件获取模块

  ```java
  //VelocityEngine.FILE_RESOURCE_LOADER_PATH配置是指用绝对路径加模块文件
  Properties properties = new Properties();
  properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getPath(inputVmFilePath));
  
  //用属性类初始化引擎
  Velocity.init(properties);
  
  //传入模块文件和编码，用引擎获取模块
  Template template = Velocity.getTemplate(getFile(inputVmFilePath), "utf-8");
  ```

- VelocityContext上下文提供模块变量的值

  ```java
  VelocityContext context = new VelocityContext();
  context.put("package_name", packageName);
  context.put("model", model);
  context.put("mapper", StringUtil.toLowerCaseFirstOne(model));
  context.put("ctime", ctime);
  VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
  ```

- Template把上下文合并到输出流中，生成了一个新文件

  ```java
  File outputFile = new File(outputFilePath);
  FileWriterWithEncoding writer = new FileWriterWithEncoding(outputFile, "utf-8");
  template.merge(context, writer);
  writer.close();
  ```

  

更多的模块加载方式参考：[Velocity中加载vm文件的三种方式](https://www.cnblogs.com/huahua035/p/4815582.html)

