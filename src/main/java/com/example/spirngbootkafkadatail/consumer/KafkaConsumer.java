package com.example.spirngbootkafkadatail.consumer;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.example.spirngbootkafkadatail.message.MessageBean;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Kafka消息消费类
 *
 * @author chen
 * @version 1.0.0
 */
@Log
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topic.order}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload String message) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        // 将接收到的消息反序列化消息实例
        MessageBean messageBean = gson.fromJson(message, new TypeToken<MessageBean>() {
        }.getType());
        // 将消息实例序列化为json格式的字符串
        String json = gson.toJson(messageBean);
        // 打印消息
        System.out.println("\n接受到的消息为：\n" + json);
    }
}
