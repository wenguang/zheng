import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zheng.upms.ztest2.common.Obj;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        String msg = (String)ctx.getBean("msg");
//        System.out.println(msg);

        Obj obj = (Obj)ctx.getBean("obj");
        obj.output();
    }
}
