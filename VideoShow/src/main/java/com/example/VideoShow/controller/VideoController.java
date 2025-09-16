package com.example.VideoShow.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
    
public class VideoController {

    @GetMapping("/video/{filename}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename) throws IOException {

        filename = filename.replaceAll("[<>:\"/\\\\|?*]", "").trim();
        System.out.println(filename);

        Path videoPath = Paths.get("C:\\Users\\ganes\\Downloads\\VideoShow\\VideoShow\\src\\main\\resources\\videos").resolve(filename).normalize();

        Resource videoResource = new UrlResource(videoPath.toUri());

        if (!videoResource.exists() || !videoResource.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(videoResource);
    }

     @GetMapping("/show")
    public String home(){
        return "app is run";
    }
}
