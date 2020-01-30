package club.huangdu94;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * 使用Java api操作ES集群测试类
 */
public class ESTest {
    private static final int PORT = 9300;
    private TransportClient client;

    @Before
    public void beforeTest() {
        System.out.println("初始化操作...");
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch-study").put("client.transport.sniff", true).build();
        //new PreBuiltTransportClient(settings);
        client = TransportClient.builder().settings(settings).build();
        client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("111.67.206.137", PORT)));
    }

    @Test
    public void esTest() {
        System.out.println(client);
    }

    @After
    public void afterTest() {
        System.out.println("连接关闭...");
        if (client != null) {
            client.close();
        }
    }
}
