package org.example.obj;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
@SPI("hello")
public interface ISay {
    String say();
}
