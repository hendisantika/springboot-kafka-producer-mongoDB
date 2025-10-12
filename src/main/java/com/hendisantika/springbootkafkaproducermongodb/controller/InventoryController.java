package com.hendisantika.springbootkafkaproducermongodb.controller;

import com.hendisantika.springbootkafkaproducermongodb.kafka.UpdatePriceProducer;
import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import com.hendisantika.springbootkafkaproducermongodb.repository.InventoryRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-kafka-producer-mongodb
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/18
 * Time: 07.19
 */
@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final ConfigurableApplicationContext context;
    private final InventoryRepository repository;

    @PostMapping(value = "/updateStock")
    public ModelAndView updateStockInventory(@ModelAttribute("id") String id, @ModelAttribute("stock") int stock) {
        int retval = repository.updateStockProduct(id, stock);

        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "editStock/{id}")
    public String updateStockInventory(Model model, @PathVariable String id) {
        Inventory inventory = repository.findProductById(id);

        model.addAttribute(inventory);

        return "updateStock";
    }

    @GetMapping(value = "editPrice/{id}")
    public String updatePriceInventory(Model model, @PathVariable String id) {
        Inventory inventory = repository.findProductById(id);

        model.addAttribute(inventory);

        return "updatePrice";
    }

    @PostMapping(value = "/updatePrice")
    public ModelAndView updatePriceInventory(@ModelAttribute("id") String id, @ModelAttribute("price") double newPrice) {
        UpdatePriceProducer producer = context.getBean("kafkaUpdatePriceProducer", UpdatePriceProducer.class);

        producer.updateDataPriceAndPublishToKafka(id, newPrice);

        return new ModelAndView("redirect:/");
    }

    @PostMapping(value = "/create")
    public ModelAndView addInventory(@ModelAttribute("inventory") Inventory inventory, Model model) {
        repository.save(inventory);

        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/create")
    public ModelAndView addInventory(HttpSession httpSession) {
        return new ModelAndView("create");
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView deleteProduct(@PathVariable String id) {
        repository.deleteById(id);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/")
    public ModelAndView getAll(HttpSession httpSession) {
        List<Inventory> result = repository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("inventories", result);

        return modelAndView;
    }
}

