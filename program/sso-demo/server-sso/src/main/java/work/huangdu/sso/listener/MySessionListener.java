package work.huangdu.sso.listener;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import work.huangdu.common.entity.ClientInfoDO;
import work.huangdu.common.util.HttpUtil;
import work.huangdu.common.util.LoggerUtil;
import work.huangdu.common.util.MockDatabaseUtil;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String token = (String)session.getAttribute("token");
        LoggerUtil.info("[session destroyed] token: " + token);
        if (StringUtils.isEmpty(token)) {
            return;
        }
        // 删除t_token表中的数据
        MockDatabaseUtil.T_TOKEN.remove(token);
        List<ClientInfoDO> clientInfoDOList = MockDatabaseUtil.T_CLIENT_INFO.remove(token);
        LoggerUtil.info("[session destroyed] client info list: " + clientInfoDOList);
        if (CollectionUtils.isEmpty(clientInfoDOList)) {
            return;
        }
        for (ClientInfoDO vo : clientInfoDOList) {
            // 获取出注册的子系统,依次调用子系统的登出的方法
            HttpUtil.sendHttpRequest(vo.getClientUrl(), vo.getJsessionId());
        }
    }
}
