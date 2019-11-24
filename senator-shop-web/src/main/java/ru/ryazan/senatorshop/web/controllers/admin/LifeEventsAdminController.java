package ru.ryazan.senatorshop.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.admin.LifeEvents;
import ru.ryazan.senatorshop.core.domain.admin.PhotoOfEvent;
import ru.ryazan.senatorshop.core.service.LifeEventsService;
import ru.ryazan.senatorshop.core.service.PhotoOfEventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LifeEventsAdminController {

    private LifeEventsService lifeEventsService;
    private PhotoOfEventService photoOfEventService;

    public LifeEventsAdminController(LifeEventsService lifeEventsService, PhotoOfEventService photoOfEventService) {
        this.lifeEventsService = lifeEventsService;
        this.photoOfEventService = photoOfEventService;
    }

    @RequestMapping("/life-events")
    public String lifeEvents(Model model){
        List<LifeEvents> lifeEvents = lifeEventsService.findAll();
        model.addAttribute("events", lifeEvents);
        return "admin/admin-life-events";
    }
    @RequestMapping("/events/create")
    public String createLifeEvent(Model model){
        LifeEvents lifeEvents = new LifeEvents();
        model.addAttribute("event", lifeEvents);
        return "admin/admin-life-events-create";
    }

    @RequestMapping(value = "/events/create", method = RequestMethod.POST)
    public String createNewEvent(@ModelAttribute("event")LifeEvents event, @RequestParam("files") MultipartFile[] files){
        lifeEventsService.save(event);
        LifeEvents newEvent = lifeEventsService.findByNameOfEvent(event.getNameOfEvent());
        List<PhotoOfEvent> photoOfEventSet = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            PhotoOfEvent photoOfEvent = new PhotoOfEvent();
            try {
                photoOfEvent.setFileData(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            photoOfEvent.setFileName(file.getOriginalFilename());
            photoOfEvent.setFileType(file.getContentType());
            photoOfEvent.setLifeEvents(newEvent);
            photoOfEventSet.add(photoOfEvent);
        });
        newEvent.setPhotos(photoOfEventSet);
        lifeEventsService.save(newEvent);

        return "redirect:/admin/life-events";

    }

    @RequestMapping("/events/delete/{id}")
    public String deleteSale(@PathVariable(name = "id") Long id){
        lifeEventsService.deleteById(id);
        return "redirect:/admin/life-events";
    }
}
