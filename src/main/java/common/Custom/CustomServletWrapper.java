package common.Custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/** 自定义httpServlet包装类，重写getParameter方法，修改参数（可以在此地方，对session进行操作，进行回话管理，重写getSession）
 * Created by paul on 2017/11/6.
 */
public class CustomServletWrapper extends HttpServletRequestWrapper {



    public CustomServletWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String parameter = super.getParameter(name);
        return null == parameter ? null : convert(parameter);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (null != parameters && parameters.length > 0) {
            parameters[0] = null == parameters[0] ? null : convert(parameters[0]);
        }
        return parameters;
    }



    /**
     * 过滤规则
     * @param target
     * @return
     */
    public String convert(String target) {
        target = target + "*****";
        return target;
    }
}
