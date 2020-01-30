package club.huangdu94.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetSocketAddress;

/**
 * ElasticSearch工具类
 *
 * @author duhuang@iflytek.com
 * @version 2020/1/30 15:11
 */
public class ElasticSearchUtils {
    private static final String IP = "111.67.206.137";
    private static final int PORT = 9300;
    /**
     * 连接实体
     */
    private static TransportClient client;

    /**
     * 获得连接实体
     *
     * @return TransportClient
     */
    public static TransportClient getClient() {
        if (client == null) {
            synchronized (ElasticSearchUtils.class) {
                if (client == null) {
                    connect();
                }
            }
        }
        return client;
    }

    /**
     * 连接
     */
    private static void connect() {
        System.out.println("初始化操作...");
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch-study").put("client.transport.sniff", true).build();
        //new PreBuiltTransportClient(settings);
        client = TransportClient.builder().settings(settings).build();
        client.addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(IP, PORT)));
    }

    /**
     * 关闭连接
     */
    public static void disconnect() {
        System.out.println("关闭连接...");
        if (client != null) {
            synchronized (ElasticSearchUtils.class) {
                if (client != null) {
                    client.close();
                }
            }
        }
    }
}
