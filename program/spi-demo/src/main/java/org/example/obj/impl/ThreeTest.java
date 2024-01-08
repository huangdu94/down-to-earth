package org.example.obj.impl;

import org.apache.dubbo.common.URL;
import org.example.obj.ITest;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class ThreeTest implements ITest {
    @Override
    public String test(URL url) {
        return
            "[three]" + url.getParameters().toString();
    }
}
