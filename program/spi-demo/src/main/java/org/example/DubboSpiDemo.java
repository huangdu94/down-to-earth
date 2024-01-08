package org.example;

import java.util.HashMap;
import java.util.Map;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.example.obj.ISay;
import org.example.obj.ITest;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class DubboSpiDemo {
    public static void main(String[] args) {
        /*
        META-INF/dubbo/internal/
        META-INF/dubbo/
        META-INF/services/
         */
        ExtensionLoader<ISay> el = ApplicationModel.defaultModel().getDefaultModule().getExtensionLoader(ISay.class);
        System.out.println(el.getDefaultExtension().say());
        for (ISay iSay : el.getActivateExtensions()) {
            System.out.println(iSay.say());
        }

        ExtensionLoader<ITest> el2 = ApplicationModel.defaultModel().getDefaultModule().getExtensionLoader(ITest.class);

        System.out.println(el2.getDefaultExtension());
        for (ITest iTest : el2.getActivateExtensions()) {
            System.out.println(iTest);
        }
        ITest iTest = el2.getAdaptiveExtension();
        Map<String, String> parameters = new HashMap<>(1);
        parameters.put("key", "two");
        URL url = new URL("test", "localhost", 0, parameters);
        System.out.println(iTest.test(url));

        System.out.println(ApplicationModel.defaultModel().getDefaultModule().getExtensionLoader(Protocol.class).getAdaptiveExtension());
    }
}
