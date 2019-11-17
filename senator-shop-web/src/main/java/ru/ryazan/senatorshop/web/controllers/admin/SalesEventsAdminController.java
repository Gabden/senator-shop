package ru.ryazan.senatorshop.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.admin.SalesEvents;
import ru.ryazan.senatorshop.core.service.SaleEventsService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SalesEventsAdminController {
    private SaleEventsService saleEventsService;

    public SalesEventsAdminController(SaleEventsService saleEventsService) {
        this.saleEventsService = saleEventsService;
    }

    @RequestMapping
    public String salesEvents(Model model){
        List<SalesEvents> salesEvents = saleEventsService.findAll();
        model.addAttribute("sales", salesEvents);
        return "";
    }
}
