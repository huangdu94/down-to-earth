package org.example.obj.impl;

import org.apache.dubbo.common.extension.Activate;
import org.example.obj.ISay;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
@Activate
public class HelloSay implements ISay {
    @Override
    public String say() {
        return "hello";
    }
}
