package work.huangdu.sso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import work.huangdu.common.constant.CommonConstant;
import work.huangdu.common.entity.ClientInfoDO;
import work.huangdu.common.util.LoggerUtil;
import work.huangdu.common.util.MockDatabaseUtil;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
@Controller
public class SsoServerController {
    @RequestMapping("/checkLogin")
    public String checkLogin(String redirectUrl, HttpSession session, Model model) {
        // 1.判断是否有全局的会话
        String token = (String)session.getAttribute("token");
        LoggerUtil.info("[check login] redirect url: " + redirectUrl + " token: " + token);
        if (StringUtils.isEmpty(token) || !MockDatabaseUtil.T_TOKEN.contains(token)) {
            LoggerUtil.info("[check login] no token or token is not exist in database, go to login.");
            model.addAttribute("redirectUrl", redirectUrl);
            return "login";
        } else {
            LoggerUtil.info("[check login] have token, redirect and with token.");
            model.addAttribute("token", token);
            return "redirect:" + redirectUrl;
        }
    }

    /**
     * 登陆功能
     */
    @RequestMapping("/login")
    public String login(String username, String password, String redirectUrl, HttpSession session, Model model) {
        LoggerUtil.info(String.format("[login] username: %s, password: %s, redirect url: %s.", username, password, redirectUrl));
        if (MockDatabaseUtil.T_USER_INFO.containsKey(username) && MockDatabaseUtil.T_USER_INFO.get(username).equals(password)) {
            // 账号密码匹配
            // 1.创建令牌信息
            String token = UUID.randomUUID().toString();
            LoggerUtil.info("[login] login success, generate token: " + token);
            // 2.创建全局的会话,把令牌信息放入会话中.
            session.setAttribute("token", token);
            // 3.需要把令牌信息放到数据库中.
            MockDatabaseUtil.T_TOKEN.add(token);
            // 4.重定向到redirectUrl,把令牌信息带上.
            model.addAttribute("token", token);
            return "redirect:" + redirectUrl;
        }
        LoggerUtil.info("[login] login fail, login again.");
        //如果账号密码有误,重新回到登录页面,还需要把redirectUrl放入request域中.
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

    /**
     * 校验token是否由统一认证中心产生的
     */
    @RequestMapping("/verify")
    @ResponseBody
    public String verifyToken(String token, String clientUrl, String jsessionid) {
        LoggerUtil.info(String.format("[verify token] token: %s, client url: %s, jsessionid: %s", token, clientUrl, jsessionid));
        if (MockDatabaseUtil.T_TOKEN.contains(token)) {
            // 把客户端的登出地址记录
            List<ClientInfoDO> clientInfoList = MockDatabaseUtil.T_CLIENT_INFO.get(token);
            if (clientInfoList == null) {
                clientInfoList = new ArrayList<>();
                MockDatabaseUtil.T_CLIENT_INFO.put(token, clientInfoList);
            }
            ClientInfoDO vo = new ClientInfoDO();
            vo.setClientUrl(clientUrl);
            vo.setJsessionId(jsessionid);
            clientInfoList.add(vo);
            LoggerUtil.info("[verify token] verify success, save client info, info list: " + clientInfoList);
            // 说明令牌有效,返回true
            return CommonConstant.TRUE;
        }
        LoggerUtil.info("[verify token] verify fail.");
        return CommonConstant.FALSE;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        LoggerUtil.info("[logout] session id: " + session.getId());
        // 销毁全局会话
        session.invalidate();
        return "logout";
    }
}
