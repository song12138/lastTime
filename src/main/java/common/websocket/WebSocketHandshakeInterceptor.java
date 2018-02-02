package common.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**  WebSocket握手拦截器用来拦截和处理客户端和服务器端分别在握手前和握手后的事件，我们这里添加这个拦截器是为了清晰的知道什么时候以及是否握手成功。
 * Created by paul on 2017/12/6.
 */
@Component
public class WebSocketHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("握手之前····");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("握手之后····");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
