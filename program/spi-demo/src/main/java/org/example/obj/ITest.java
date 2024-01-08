package org.example.obj;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
@SPI("one")
public interface ITest {
    @Adaptive("key")
    String test(URL url);
}
