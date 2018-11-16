package com.hendisantika.springbootkafkaproducermongodb.kafka;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.14
 */
public interface UpdatePriceProducer {

    void updateDataPriceAndPublishToKafka(String id, double price);
}