package single;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @Author: qingshan
 * @Date: 2019/9/26 23:09
 * @Description: 咕泡学院，只为更好的你
 */
public class SingleTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.setCodec(new org.redisson.client.codec.StringCodec());

        //指定使用单节点部署方式
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);

        //首先获取redis中的key-value对象，key不存在没关系
        RBucket<String> keyObject = redisson.getBucket("redisson-single-key");
        //如果key存在，就设置key的值为新值value
        //如果key不存在，就设置key的值为value
        keyObject.set("value");

        //最后关闭RedissonClient
        redisson.shutdown();
    }
}
