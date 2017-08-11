package common.interceptor;

import common.dataSource.DataSourceContextHolder;
import common.dataSource.DataSourceType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**根据url判断数据源
 * Created by paul on 2017/8/11.
 */
public class UrlDsInterceptor implements HandlerInterceptor {

    public  String[] DS1;

    public  String[] DS2;

    public String[] getDS1() {
        return DS1;
    }

    public void setDS1(String[] DS1) {
        this.DS1 = DS1;
    }

    public String[] getDS2() {
        return DS2;
    }

    public void setDS2(String[] DS2) {
        this.DS2 = DS2;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取url
        String url = request.getRequestURI().replace(request.getContextPath(),"");
//        String url = request.getContextPath();

        //匹配url切换数据源
        for (int i = 0; i < DS2.length; i++) {
            if(DS2[i].equals(url)){
                DataSourceContextHolder.setDsType(DataSourceType.DATASOURCE2);
                return true;
            }
        }
        //默认数据源
        DataSourceContextHolder.setDsType(DataSourceType.DATASOURCE1);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
