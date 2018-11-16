package com.hendisantika.springbootkafkaproducermongodb.repository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.15
 */
public interface InventoryRepositoryCustom {

    int updateStockProduct(String id, int stock);

    int updatePriceProduct(String id, double price);
}