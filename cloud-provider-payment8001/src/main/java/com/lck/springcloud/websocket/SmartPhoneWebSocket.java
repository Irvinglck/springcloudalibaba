package com.lck.springcloud.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 前后端交互的类实现消息的接收推送(自己发送给自己)
 *
 * @ServerEndpoint(value = "/smartPhoneWs/{userId}/{businessId") 前端通过此URI和后端交互，建立连接
 */
@Slf4j
@Component
@ServerEndpoint(value = "/smartPhoneWs/{userId}/{businessId}")
public class SmartPhoneWebSocket {
    private Session session; //建立连接的会话
    private String userId; //当前连接用户id   路径参数
    private String businessId; //营业厅列表id   路径参数



    /**
     * 存放存活的Session集合(map保存)
     */
    private static ConcurrentHashMap<String, SmartPhoneWebSocket> livingSession = new ConcurrentHashMap<>();

    /**
     * 建立连接的回调
     * session 建立连接的会话
     * userId 当前连接用户id   路径参数
     */
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("userId") String userId,
                       @PathParam("businessId") String businessId) {
        this.session = session;
        this.userId = userId;
        this.businessId = businessId;
        livingSession.put(userId, this);

        log.info("id为[{}]的用户进入WebSocket连接 ! 当前在线人数为 [{}] 人 ,营业厅id为[{}]"
                , userId, livingSession.size(), businessId);
        //发送一个消息给mq,获取用户信息
        sendMessageById(userId, businessId);
    }


    /**
     * 关闭连接的回调
     * 移除用户在线状态
     */
    @OnClose
    public void onClose() {
        livingSession.remove(userId);
        log.debug("[ WebSocket ]用户id为 : {} 的用户退出WebSocket连接 ! 当前在线人数为 : {} 人 !", userId, livingSession.size());
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("userId") String userId) {
        log.info("收到用户id为 : {} 的用户发送的消息 ! 消息内容为 :{} ", userId, message);
//        sendMessageToAll(userId + " : " + message);
        sendMessageBySession(session, message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket发生错误");
        log.error(error.getStackTrace() + "");
    }

    /**
     * 根据userId发送给用户
     *
     * @param userId
     * @param message
     */
    public void sendMessageById(String userId, String message) {
        livingSession.forEach((sessionId, session) -> {
            //发给指定的接收用户
            if (userId.equals(session.userId)) {
                sendMessageBySession(session.session, message);
            }
        });
    }

    /**
     * 根据Session发送消息给用户
     *
     * @param session
     * @param message
     */
    public void sendMessageBySession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("[ WebSocket ]给用户发送消息失败");
            e.printStackTrace();
        }
    }

    /**
     * 给在线用户发送消息
     *
     * @param message
     */
    public void sendMessageOnline(String message) {
        livingSession.forEach((sessionId, session) -> {
            if (session.session.isOpen()) {
                sendMessageBySession(session.session, message);
            }
        });
    }
}
