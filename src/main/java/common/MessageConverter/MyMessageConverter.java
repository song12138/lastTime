package common.MessageConverter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;
import org.springframework.util.StreamUtils;
import pro.entity.User;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by paul on 2017/12/27.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<User> {
    public MyMessageConverter() {
        //新建一个我们自定义的媒体类型：application/x-wisely
        super(new MediaType("application","x-wisely", Charset.forName("UTF-8")));
    }

    /**
     *表示这个MessageConverter只处理User这个类
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    /**
     * 处理请求的数据，转换成user
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new User(tempArr[0]+"",tempArr[1]+"");
    }

    /**
     * 处理输出数据到response
     * @param user
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello：" + user.getUsername() + "-" + user.getPassword();
        outputMessage.getBody().write(out.getBytes());

    }
}
