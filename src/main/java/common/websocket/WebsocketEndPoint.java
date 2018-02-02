package common.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**  这个类处理来之浏览器（客户端）的WebSocket请求。在这个例子中，我们创建一个叫WebSocketEndPoint的类，
 * 并让它集成TextWebsocketHandler类。然后重写父类方法handlerTextMessage(),每当客户端发送信息过来，都会由这个函数接收并处理。

 当然这里还可以重写其他方法，如afterConnectionEstablished、afterConnectionClosed、handleTransportError 等等

 扩展：这里可以将登录用户保存到对象中，然后可以实现点对点消息发送、发送所有用户等功能。
 * Created by paul on 2017/12/7.
 */
public class WebsocketEndPoint extends TextWebSocketHandler {

    private static List<WebSocketSession> sessionList = new ArrayList<>();

    private static Logger logger = LoggerFactory.getLogger(WebsocketEndPoint.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        logger.debug("WebSocketEndPoint.handlerTextMessage...");

        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        sessionList.forEach((se)->{
            try {
                se.sendMessage(returnMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        session.sendMessage(returnMessage);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionList.add(session);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionList.remove(session);
    }


}
