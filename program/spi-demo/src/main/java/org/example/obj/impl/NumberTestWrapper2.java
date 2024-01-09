package org.example.obj.impl;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Wrapper;
import org.example.obj.ITest;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
@Wrapper(order = 0)
public class NumberTestWrapper2 implements ITest {
    private final ITest iTest;

    public NumberTestWrapper2(ITest iTest) {
        this.iTest = iTest;
    }

    @Override
    public String test(URL url) {
        System.out.println("[wrapper2]");
        return iTest.test(url);
    }
}