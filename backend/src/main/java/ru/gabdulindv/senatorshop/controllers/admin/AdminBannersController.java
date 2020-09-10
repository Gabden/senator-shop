package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.gabdulindv.senatorshop.model.sales.Banner;
import ru.gabdulindv.senatorshop.service.BannerService;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminBannersController {
    private BannerService bannerService;

    public AdminBannersController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @RequestMapping("/banner/{id}")
    public ResponseEntity getBanner(@PathVariable("id") Long id) {
        Optional<Banner> banner = bannerService.findById(id);
        return ResponseEntity.ok(banner.orElseThrow(RuntimeException::new));
    }

    @RequestMapping(value = "/banner/create/img", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity createBanner(@RequestParam("file") CommonsMultipartFile file) {
        Banner banner = new Banner();
        banner.setFileType(file.getContentType());
        banner.setFileName(file.getName());
        banner.setFileData(file.getBytes());
        bannerService.save(banner);
        return ResponseEntity.ok(banner.getId());
    }

    @RequestMapping(value = "/banner/update/url/{id}", method = RequestMethod.POST)
    public ResponseEntity updateBannerUrl(@PathVariable("id") Long id, @RequestParam("url") String url) {
        Optional<Banner> banner = bannerService.findById(id);
        if (banner.isPresent()) {
            banner.get().setBannerUrl(url);
            bannerService.save(banner.get());
            return ResponseEntity.ok("Banner url updated");
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/banner/update/img/{id}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity bannerImg(@PathVariable("id") Long id, @RequestParam("file") CommonsMultipartFile file) {
        Optional<Banner> banner = bannerService.findById(id);
        if (banner.isPresent()) {
            banner.get().setFileType(file.getContentType());
            banner.get().setFileName(file.getName());
            banner.get().setFileData(file.getBytes());
            bannerService.save(banner.get());
            return ResponseEntity.ok("Banner img updated");
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/banner/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteBanner(@PathVariable("id") Long id) {
        bannerService.deleteById(id);
        return ResponseEntity.ok("Banner has been deleted");
    }
}
