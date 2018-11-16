package com.hendisantika.springbootkafkaproducermongodb.controller;

import com.hendisantika.springbootkafkaproducermongodb.kafka.UpdatePriceProducer;
import com.hendisantika.springbootkafkaproducermongodb.model.Inventory;
import com.hendisantika.springbootkafkaproducermongodb.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
public class InventoryController {

    @Autowired
    ConfigurableApplicationContext context;
    @Autowired
    private InventoryRepository repository;

    @RequestMapping(value = "/updateStock", method = RequestMethod.POST)
    public ModelAndView updateStockInventory(@ModelAttribute("id") String id, @ModelAttribute("stock") int stock) {
        int retval = repository.updateStockProduct(id, stock);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "editStock/{id}", method = RequestMethod.GET)
    public String updateStockInventory(Model model, @PathVariable String id) {
        Inventory inventory = repository.findById(id);

        model.addAttribute(inventory);

        return "updateStock";
    }

    @RequestMapping(value = "editPrice/{id}", method = RequestMethod.GET)
    public String updatePriceInventory(Model model, @PathVariable String id) {
        Inventory inventory = repository.findById(id);

        model.addAttribute(inventory);

        return "updatePrice";
    }

    @RequestMapping(value = "/updatePrice", method = RequestMethod.POST)
    public ModelAndView updatePriceInventory(@ModelAttribute("id") String id, @ModelAttribute("price") double newPrice) {
        UpdatePriceProducer producer = context.getBean("kafkaUpdatePriceProducer", UpdatePriceProducer.class);

        producer.updateDataPriceAndPublishToKafka(id, newPrice);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView addInventory(@ModelAttribute("inventory") Inventory inventory, Model model) {
        repository.save(inventory);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView addInventory(HttpSession httpSession) {
        return new ModelAndView("create");
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable String id) {
        repository.deleteById(id);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/")
    public ModelAndView getAll(HttpSession httpSession) {
        List<Inventory> result = repository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("inventories", result);

        return modelAndView;
    }
}

