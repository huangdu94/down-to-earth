package work.huangdu.common.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import work.huangdu.common.constant.CommonConstant;

/**
 * HTTP请求工具类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
public class HttpUtil {
    /**
     * 模拟浏览器的请求
     *
     * @param urlStr 发送请求的地址
     * @param params 请求参数
     * @return 响应内容字符串
     */
    public static String sendHttpRequest(String urlStr, Map<String, Object> params) {
        HttpURLConnection conn = null;
        String responseContent = null;
        try {
            // 建立URL连接对象
            URL url = new URL(urlStr);
            // 创建连接
            conn = (HttpURLConnection)url.openConnection();
            // 设置请求的方式(需要是大写的)
            conn.setRequestMethod(CommonConstant.POST);
            //设置需要输出
            conn.setDoOutput(true);
            // 判断是否有参数
            if (!CollectionUtils.isEmpty(params)) {
                StringBuilder sb = new StringBuilder();
                for (Entry<String, Object> entry : params.entrySet()) {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                // sb.substring(1)去除最前面的&
                conn.getOutputStream().write(sb.substring(1).getBytes(StandardCharsets.UTF_8));
            }
            //发送请求到服务器
            conn.connect();
            //获取远程响应的内容.
            responseContent = StreamUtils.copyToString(conn.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(conn)) {
                conn.disconnect();
            }
        }
        return responseContent;
    }

    /**
     * 模拟浏览器的请求
     *
     * @param urlStr     发送请求的地址
     * @param jesssionId 会话Id
     */
    public static void sendHttpRequest(String urlStr, String jesssionId) {
        HttpURLConnection conn = null;
        try {
            //建立URL连接对象
            URL url = new URL(urlStr);
            //创建连接
            conn = (HttpURLConnection)url.openConnection();
            //设置请求的方式(需要是大写的)
            conn.setRequestMethod(CommonConstant.POST);
            //设置需要输出
            conn.setDoOutput(true);
            conn.addRequestProperty("Cookie", "JSESSIONID=" + jesssionId);
            //发送请求到服务器
            conn.connect();
            conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(conn)) {
                conn.disconnect();
            }
        }
    }
}
