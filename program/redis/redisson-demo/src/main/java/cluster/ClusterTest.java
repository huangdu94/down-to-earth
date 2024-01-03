package cluster;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Author: qingshan
 * @Date: 2019/9/26 23:14
 * @Description: 咕泡学院，只为更好的你
 */
public class ClusterTest {
    public static void main(String[] args) {

        //创建配置
        Config config = new Config();
        config.setCodec(new org.redisson.client.codec.StringCodec());

        //指定使用集群部署方式
        config.useClusterServers()
        // 集群状态扫描间隔时间，单位是毫秒
        .setScanInterval(2000)
        //cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
        .addNodeAddress("redis://192.168.8.207:7291" )
        .addNodeAddress("redis://192.168.8.207:7292")
        .addNodeAddress("redis://192.168.8.207:7293")
        .addNodeAddress("redis://192.168.8.207:7294")
        .addNodeAddress("redis://192.168.8.207:7295")
        .addNodeAddress("redis://192.168.8.207:7296");

        RedissonClient redisson = Redisson.create(config);

        //首先获取redis中的key-value对象，key不存在没关系
        RBucket<String> keyObject = redisson.getBucket("redisson-cluster-key");
        //如果key存在，就设置key的值为新值value
        //如果key不存在，就设置key的值为value
        keyObject.set("value");

        //最后关闭RedissonClient
        redisson.shutdown();
    }
}
