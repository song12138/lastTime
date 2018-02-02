package common.customTag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by paul on 2018/2/2.
 */
public class HelloTag2 extends SimpleTagSupport {
    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        getJspBody().invoke(sw);
        getJspContext().getOut().println(sw.toString());
    }
}
