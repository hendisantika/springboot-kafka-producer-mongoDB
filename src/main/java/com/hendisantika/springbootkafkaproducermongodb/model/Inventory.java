package com.hendisantika.springbootkafkaproducermongodb.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.09
 */

@Data
@ToString
public class Inventory {
    public static final String COLLECTION_NAME = "inventory";

    @Id
    private String id;

    private String productName;
    private int stock;
    private double price;
}
