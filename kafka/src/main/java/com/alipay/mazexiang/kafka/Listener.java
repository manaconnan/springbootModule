/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Listener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @KafkaListener(topics = {"topic-test"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka��record "+record);
        logger.info("kafka��key: " + record.key());
        logger.info("kafka��value: " + record.value().toString());
    }
}