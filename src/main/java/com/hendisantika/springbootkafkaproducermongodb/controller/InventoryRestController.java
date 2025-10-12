package com.hendisantika.springbootkafkaproducermongodb.controller;

import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import com.hendisantika.springbootkafkaproducermongodb.repository.InventoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.20
 */
@RequestMapping(value = "inventory")
@RestController
public class InventoryRestController {

    @Autowired
    private InventoryRepository repository;

    @Operation(summary = "GetInventoryData", description = "Get inventory by code")
    @RequestMapping(method = RequestMethod.GET, path = "/getInventoryByCode", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Failure")})
    public Inventory getInventory(
            @Parameter(description = "Inventory Id", required = false)
            @RequestParam(value = "id", defaultValue = "1") String id) {
        return repository.findById(id).orElse(null);
    }
}