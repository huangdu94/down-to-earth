package work.huangdu.crm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import work.huangdu.common.filter.SsoFilter;
import work.huangdu.common.util.SsoClientUtil;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/4
 */
public class SsoClientFilter implements Filter {
    private SsoFilter ssoFilter;

    @Override
    public void init(FilterConfig filterConfig) {
        this.ssoFilter = new SsoFilter(new SsoClientUtil());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ssoFilter.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
    }
}
