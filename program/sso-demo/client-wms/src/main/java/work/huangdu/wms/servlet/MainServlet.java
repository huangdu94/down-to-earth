package work.huangdu.wms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import work.huangdu.common.util.LoggerUtil;
import work.huangdu.common.util.SsoClientUtil;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
@WebServlet(name = "MainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    private SsoClientUtil ssoClientUtil;

    @Override
    public void init() {
        this.ssoClientUtil = new SsoClientUtil();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggerUtil.info("[main] request.");
        req.setAttribute("serverLogOutUrl", ssoClientUtil.getServerLogoutUrl());
        req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
    }
}
