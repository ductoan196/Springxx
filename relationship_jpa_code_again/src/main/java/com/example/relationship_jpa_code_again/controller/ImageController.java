package com.example.relationship_jpa_code_again.controller;

import com.example.relationship_jpa_code_again.entity.Image;
import com.example.relationship_jpa_code_again.repository.ImageRepository;
import com.example.relationship_jpa_code_again.repository.UserRepository;
import com.example.relationship_jpa_code_again.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/files")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    private final UserRepository userRepository;

    //1. Xem file
    @GetMapping("{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
        //image/png
        //image/jpg
        //application.pdf
    }

    //2. Xóa file
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();   //204- No content
    }
}
