package common.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by paul on 2017/10/9.
 */
public class IpFilter implements Filter {

    private FilterConfig filterConfig;

    private String rejectedIp;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig; // 从Web务器获取过滤器配置对象
        this.rejectedIp = filterConfig.getInitParameter("RejectedlP"); // 从配置中取得过滤lP
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String remoteIp = request.getRemoteAddr();
        int i = remoteIp.lastIndexOf(".");
        int r = rejectedIp.lastIndexOf(".");
        String rejIp = rejectedIp.substring(0, r);
        if (rejIp.equals(remoteIp.substring(0, i))) {

            response.getWriter().write("qwer");


            return; // 阻塞，直接返Web回客户端
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
