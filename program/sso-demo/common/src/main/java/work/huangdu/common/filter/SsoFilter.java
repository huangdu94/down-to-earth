package work.huangdu.common.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import work.huangdu.common.constant.CommonConstant;
import work.huangdu.common.util.HttpUtil;
import work.huangdu.common.util.LoggerUtil;
import work.huangdu.common.util.SsoClientUtil;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/6
 */
public class SsoFilter {
    private final SsoClientUtil ssoClientUtil;

    public SsoFilter(SsoClientUtil ssoClientUtil) {
        LoggerUtil.info("[sso filter] init: " + ssoClientUtil);
        this.ssoClientUtil = ssoClientUtil;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        // 1.判断是否有局部的会话
        Boolean isLogin = (Boolean)session.getAttribute("isLogin");
        LoggerUtil.info("[sso filter] isLogin: " + isLogin);
        if (Objects.nonNull(isLogin) && isLogin) {
            //有局部会话,直接放行.
            chain.doFilter(request, response);
            return;
        }
        // 判断地址栏中是否有携带token参数.
        String token = req.getParameter("token");
        LoggerUtil.info("[sso filter] token: " + token);
        if (StringUtils.isNoneBlank(token)) {
            // token信息不为null,说明地址中包含了token,拥有令牌.
            // 判断token信息是否由认证中心产生的.
            String urlStr = ssoClientUtil.getServerVerifyUrl();
            Map<String, Object> params = new HashMap<>(3);
            params.put("token", token);
            params.put("clientUrl", ssoClientUtil.getClientLogoutUrl());
            params.put("jsessionid", session.getId());
            LoggerUtil.info("[sso filter] have token, send verify request: " + params);
            try {
                String isVerify = HttpUtil.sendHttpRequest(urlStr, params);
                LoggerUtil.info("[sso filter] verify result: " + isVerify);
                if (CommonConstant.TRUE.equals(isVerify)) {
                    //如果返回的字符串是true,说明这个token是由统一认证中心产生的.
                    //创建局部的会话.
                    session.setAttribute("isLogin", true);
                    //放行该次的请求
                    chain.doFilter(request, response);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LoggerUtil.info("[sso filter] no token or verify fail, go to check login.");
        // 没有局部会话,重定向到统一认证中心,检查是否有其他的系统已经登录过.
        ssoClientUtil.redirectToSsoUrl(req, resp);
    }
}
