package work.huangdu.common.util;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SSO相关URL配置工具类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
public class SsoClientUtil {
    /**
     * 统一认证中心地址,在sso.properties配置
     */
    private final String serverUrlPrefix;
    /**
     * 统一认证中心地址,在sso.properties配置
     */
    private final String clientHostUrl;

    /**
     * 初始化配置由工具类使用方调用
     */
    public SsoClientUtil() {
        Properties ssoProperties = new Properties();
        try {
            ssoProperties.load(SsoClientUtil.class.getClassLoader().getResourceAsStream("sso.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverUrlPrefix = ssoProperties.getProperty("server.url");
        clientHostUrl = ssoProperties.getProperty("client.url");
    }

    /**
     * 根据request获取跳转到统一认证中心的地址
     * 通过Response跳转到指定的地址
     */
    public void redirectToSsoUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = getRedirectUrl(request);
        String url = serverUrlPrefix + "/checkLogin?redirectUrl=" + redirectUrl;
        response.sendRedirect(url);
    }

    /**
     * 获取客户端的完整登出地址
     */
    public String getClientLogoutUrl() {
        return clientHostUrl + "/logout";
    }

    /**
     * 获取认证中心的登出地址
     */
    public String getServerLogoutUrl() {
        return serverUrlPrefix + "/logout";
    }

    /**
     * 获取认证中心token验证接口地址
     */
    public String getServerVerifyUrl() {
        return serverUrlPrefix + "/verify";
    }

    /**
     * 当客户端请求被拦截,跳往统一认证中心,需要带redirectUrl的参数,统一认证中心登录后回调的地址
     * 通过Request获取这次请求的地址
     */
    private String getRedirectUrl(HttpServletRequest request) {
        return clientHostUrl + request.getServletPath();
    }

    @Override
    public String toString() {
        return "SsoClientUtil{" +
            "serverUrlPrefix='" + serverUrlPrefix + '\'' +
            ", clientHostUrl='" + clientHostUrl + '\'' +
            '}';
    }
}
