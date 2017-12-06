package common.filter;

import common.custom.CustomServletWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**  可以对参数等等进行一些过滤
 * Created by paul on 2017/11/8.
 */
public class ParameterFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomServletWrapper servletWrapper = new CustomServletWrapper(request);
        doFilter(servletWrapper,response,filterChain);
    }
}
