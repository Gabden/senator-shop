package ru.ryazan.senatorshop.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.admin.SalesEvents;
import ru.ryazan.senatorshop.core.service.SaleEventsService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class SalesEventsAdminController {
    private SaleEventsService saleEventsService;

    public SalesEventsAdminController(SaleEventsService saleEventsService) {
        this.saleEventsService = saleEventsService;
    }

    @RequestMapping("/sales")
    public String salesEvents(Model model){
        List<SalesEvents> salesEvents = saleEventsService.findAll();
        model.addAttribute("sales", salesEvents);
        return "admin/admin-sales";
    }

    @RequestMapping("/sales/create")
    public String createSale(Model model){
        SalesEvents salesEvents = new SalesEvents();
        model.addAttribute("event", salesEvents);
        return "admin/admin-sales-create";

    }

    @RequestMapping(value = "/sales/create", method = RequestMethod.POST)
    public String saveSaleToDB(@ModelAttribute(name = "event")SalesEvents event,@RequestParam("file") MultipartFile file){
        try {
            event.setFileData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
         event.setFileName(file.getOriginalFilename());
         event.setFileType(file.getContentType());
         saleEventsService.save(event);

        return "redirect:/admin/sales";

    }

    @RequestMapping("/sales/delete/{id}")
    public String deleteSale(@PathVariable(name = "id") Long id){
        saleEventsService.deleteById(id);
        return "redirect:/admin/sales";
    }
}
