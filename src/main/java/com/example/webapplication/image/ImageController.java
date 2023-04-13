package com.example.webapplication.image;

import com.example.webapplication.image.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {
    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }


    @GetMapping("/user-images/{id}")
    public ResponseEntity<?> getUserImages(@PathVariable int id,
                                           @RequestParam(defaultValue = "0") Integer pageNo,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(imageService.getUserImages(id,pageNo,pageSize,sortBy), HttpStatus.OK);

    }
}
