package zheng.upms.ztest2.common;

import org.springframework.beans.factory.annotation.Autowired;

public class Obj {
    @Autowired
    Util util;

    public void output() {
        util.output();
    }
}
