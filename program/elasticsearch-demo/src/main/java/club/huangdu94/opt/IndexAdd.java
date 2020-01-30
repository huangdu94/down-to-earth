package club.huangdu94.opt;

import club.huangdu94.entity.Product;
import club.huangdu94.utils.ElasticSearchUtils;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.util.Date;

/**
 * 新增索引
 *
 * @author duhuang@iflytek.com
 * @version 2020/1/30 15:25
 */
public class IndexAdd {
    private static final String INDEX = "store";
    private static final String TYPE = "product";

    public static void addOneProductByJson() {
        TransportClient client = ElasticSearchUtils.getClient();
        String jsonStr = JSON.toJSONString(new Product("战斗机", new Date(), 10000000.0));
        IndexResponse response = client.prepareIndex(INDEX, TYPE).setSource(jsonStr).setContentType(XContentType.JSON).execute().actionGet();
        System.out.printf("新增内容：%s,新增结果：%s", jsonStr, response.toString());
        ElasticSearchUtils.disconnect();
    }
}
