package com.zheng.upms.rpc;

import com.zheng.upms.dao.mapper.UpmsLogMapper;
import com.zheng.upms.rpc.service.impl.UpmsUserServiceImpl;

public class SomeTest {
    public <T> void getBean(Class<T> clazz) {
        System.out.println(clazz);
    }

    public static void main(String[] args) {
        SomeTest test = new SomeTest();
        test.getBean(UpmsLogMapper.class);

        UpmsUserServiceImpl uli = new UpmsUserServiceImpl();
        System.out.println();
    }
}
