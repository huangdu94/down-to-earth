package work.huangdu.wms.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import work.huangdu.common.util.LoggerUtil;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LoggerUtil.info("[logout] request.");
        req.getSession().invalidate();
    }
}
