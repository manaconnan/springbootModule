/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/kafka")
public class CollectController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendKafka(HttpServletRequest request, HttpServletResponse response) {
        logger.info("send kafka");
        try {
            String message = request.getParameter("message");
            logger.info("kafka的消息={}",message);
            kafkaTemplate.send("topic-test","key",message);
            //key可以为null
            //kafkaTemplate.send("topic-test", message);
            logger.info("发送kafka成功.");
            return  "发送kafka成功";
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return  "发送kafka失败";
        }
    }

}