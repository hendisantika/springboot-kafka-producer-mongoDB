package com.hendisantika.springbootkafkaproducermongodb.kafka;

import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import com.hendisantika.springbootkafkaproducermongodb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
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
@RequiredArgsConstructor
public class KafkaUpdatePriceProducer implements UpdatePriceProducer {

    private final InventoryRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic:inventoryTest}")
    private String topic;

    @Override
    public void updateDataPriceAndPublishToKafka(String id, double price) {

        Inventory inventory = repository.findProductById(id);
        //check if new price < old price (diskon)
        //send to kafka
        if (inventory != null && price < inventory.getPrice()) {
            kafkaTemplate.send(topic, id, Double.toString(price));
        }
        repository.updatePriceProduct(id, price);
    }
}