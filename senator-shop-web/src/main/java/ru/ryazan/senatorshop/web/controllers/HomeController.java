package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.admin.LifeEvents;
import ru.ryazan.senatorshop.core.domain.admin.PhotoOfEvent;
import ru.ryazan.senatorshop.core.domain.admin.SalesEvents;
import ru.ryazan.senatorshop.core.service.LifeEventsService;
import ru.ryazan.senatorshop.core.service.PhotoOfEventService;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.core.service.SaleEventsService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    private ProductService productService;
    private SaleEventsService salesEventsService;
    private LifeEventsService lifeEventsService;
    private PhotoOfEventService photoOfEventService;

    public HomeController(ProductService productService, SaleEventsService salesEventsService
                          , PhotoOfEventService photoOfEventService, LifeEventsService lifeEventsService) {
        this.productService = productService;
        this.salesEventsService = salesEventsService;
        this.photoOfEventService = photoOfEventService;
        this.lifeEventsService = lifeEventsService;
    }

    @RequestMapping({"","/", "/index"})
    public String main(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
        Page<Product> products = productService.findAll(pageable);
        int totalPages = products.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        List<SalesEvents> events = salesEventsService.findAll();
        model.addAttribute("events", events);
        model.addAttribute("products", products);
        model.addAttribute("orders", products);
        model.addAttribute("url", "/");
        return "home";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/reserve")
    public String reserve(){
        return "reserve";
    }

    @RequestMapping("/rules")
    public String rules(){
        return "rules";
    }

    @RequestMapping("/business")
    public String business(){
        return "for-business";
    }

    @RequestMapping("/confidential")
    public String confidential(){
        return "confidential";
    }

    @RequestMapping("/events")
    public String events(Model model){
        List<LifeEvents> events = lifeEventsService.findAll();
        events.forEach(event -> {
            List<PhotoOfEvent> photoOfEvents = photoOfEventService.findAllByLifeEvents_Id(event.getId());
            String modelKey = "photos" + event.getId().toString();
            model.addAttribute(modelKey, photoOfEvents);
        });
        model.addAttribute("events", events);
        return "events";
    }

}
