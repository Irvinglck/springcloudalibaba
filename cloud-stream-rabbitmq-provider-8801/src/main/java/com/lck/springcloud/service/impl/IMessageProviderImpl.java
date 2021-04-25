package com.lck.springcloud.service.impl;

import com.lck.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class) // 可以理解为是一个消息的发送管道的定义
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息的发送管道

    @Override
    public String send() {

        String uuid= UUID.randomUUID().toString();
        // 创建并发送消息
        output.send(MessageBuilder.withPayload(uuid).build());
        System.out.println("uuid****** "+uuid);
        return uuid;
    }
}
