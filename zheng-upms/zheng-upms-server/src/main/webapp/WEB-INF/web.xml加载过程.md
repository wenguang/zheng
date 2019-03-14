####容器对于web.xml的加载过程是context-param >> listener  >> fileter  >> servlet




1、在启动Web项目时，容器(比如Tomcat)会读web.xml配置文件中的两个节点`<listener>`和`<contex-param>`。

2、接着容器会创建一个ServletContext(上下文),应用范围内即整个WEB项目都能使用这个上下文。

3、接着容器会将读取到`<context-param>`转化为键值对,并交给ServletContext。

4、容器创建`<listener></listener>`中的类实例,即创建监听（备注：listener定义的类可以是自定义的类但必须需要继承ServletContextListener）。

5、在监听的类中会有一个contextInitialized(ServletContextEvent event)初始化方法，在这个方法中可以通过event.getServletContext().getInitParameter("contextConfigLocation") 来得到context-param 设定的值。在这个类中还必须有一个contextDestroyed(ServletContextEvent event) 销毁方法.用于关闭应用前释放资源，比如说数据库连接的关闭。

6、得到这个context-param的值之后,你就可以做一些操作了.注意,这个时候你的WEB项目还没有完全启动完成.这个动作会比所有的Servlet都要早。