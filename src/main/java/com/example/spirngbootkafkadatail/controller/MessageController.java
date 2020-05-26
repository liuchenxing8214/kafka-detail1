package com.example.spirngbootkafkadatail.controller;

import com.example.spirngbootkafkadatail.message.MessageBean;
import com.example.spirngbootkafkadatail.producer.KafkaProducer;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息控制器
 *
 * @author chen
 * @version 1.0.0
 */
@Log
@Controller
@RequestMapping("/message")
public class MessageController {

    @Resource
    private KafkaProducer kafkaProducer;

    /**
     * 生成消息
     *
     * @return
     */
    @RequestMapping("create")
    @ResponseBody
    public Map<String, Object> create() {
        for(int i=0;i<1001;i++){
            MessageBean messageBean = new MessageBean();
            StringBuffer buf =new StringBuffer();
            String uuid = UUID.randomUUID().toString();
            messageBean.setUuid(buf.append(uuid).append("我爱你").append(i).toString());
            messageBean.setDate(new Date());
            // 将消息发送到 kafka
            kafkaProducer.sendMessage(messageBean);
        }
        // 创建消息
        Map<String, Object> model = new HashMap<>();
        // 返回成功信息
        model.put("resultCode", 1);
        model.put("resultMsg", "success");
        model.put("messageBean", null);
        return model;
    }

}