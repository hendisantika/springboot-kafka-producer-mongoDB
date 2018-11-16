package com.hendisantika.springbootkafkaproducermongodb.repository;

import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.15
 */
public interface InventoryRepository extends MongoRepository<Inventory, String>, InventoryRepositoryCustom {

    List<Inventory> findAll();

    Inventory findById(String id);
}