package com.hendisantika.springbootkafkaproducermongodb.kafka;

import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import com.hendisantika.springbootkafkaproducermongodb.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.14
 */
@Component
public class KafkaUpdatePriceProducer implements UpdatePriceProducer {

    @Autowired
    private InventoryRepository repository;

    private MessageChannel toKafka;

    @Autowired
    public KafkaUpdatePriceProducer(@Qualifier("toKafka") MessageChannel toKafka) {
        this.toKafka = toKafka;
    }

    @Override
    public void updateDataPriceAndPublishToKafka(String id, double price) {

        Inventory inventory = repository.findById(id);
        //check if new price < old price (diskon)
        //send to kafka
        if (price < inventory.getPrice()) {
            Message<?> content = new GenericMessage<String>(Double.toString(price));
            toKafka.send(content);
        }
        repository.updatePriceProduct(id, price);
    }
}