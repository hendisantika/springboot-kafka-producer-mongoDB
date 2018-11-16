package com.hendisantika.springbootkafkaproducermongodb.controller;

import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import com.hendisantika.springbootkafkaproducermongodb.repository.InventoryRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "GetInventoryData", nickname = "getInventory")
    @RequestMapping(method = RequestMethod.GET, path = "/getInventoryByCode", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "Inventory Id",
            required = false, dataType = "string", paramType = "query", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Inventory.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Inventory getInventory(@RequestParam(value = "id", defaultValue = "1") String id) {
        return repository.findById(id);
    }
}