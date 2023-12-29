package sentinel;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Author: qingshan
 * @Date: 2019/9/26 23:12
 * @Description: 咕泡学院，只为更好的你
 */
public class SentinelTest {
    public static void main(String[] args) {
        //创建配置
        Config config = new Config();
        config.setCodec(new org.redisson.client.codec.StringCodec());

        //指定使用哨兵部署方式
        config.useSentinelServers()
        .setMasterName("redis-master")
        .addSentinelAddress("redis://192.168.8.203:26379")
        .addSentinelAddress("redis://192.168.8.204:26379")
        .addSentinelAddress("redis://192.168.8.205:26379");

        RedissonClient redisson = Redisson.create(config);
        //首先获取redis中的key-value对象，key不存在没关系
        RBucket<String> keyObject = redisson.getBucket("redisson-sitinel-key");
        //如果key存在，就设置key的值为新值value
        //如果key不存在，就设置key的值为value
        keyObject.set("value");

        //最后关闭RedissonClient
        redisson.shutdown();
    }
}
