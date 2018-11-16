package com.baizhi.util;

/**
 * Created by wdwhwn on 2018/11/16.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String test(String name) {
        System.out.println("实现类别调用了");
        return "hello world"+name;
    }
}
