package com.example.spirngbootkafkadatail.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.spirngbootkafkadatail.message.MessageBean;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Kafka消息生产类
 *
 * @author  liuchen
 * @version 1.0.0
 */
@Log
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.order}")
    private String topicOrder;

    /**
     * 发送消息
     *
     * @param messageBean 消息实例
     */
    public void sendMessage(MessageBean messageBean) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        // 将消息实例序列化为json格式的字符串
        String message = gson.toJson(messageBean);
        // 发送消息
        kafkaTemplate.send(topicOrder, message);
        // 打印消息
        System.out.println("\n发送的消息为：\n" + message);
    }
}
