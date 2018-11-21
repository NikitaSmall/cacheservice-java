package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import java.io.IOException;

@RestController
public class HelloController {
    private Cache cache = new Cache();

    @GetMapping("/")
    public ResponseEntity<byte[]> getFromCache(@RequestParam("key") String key) {
        byte[] data = this.cache.get(key);
        if (data == null) {
          return new ResponseEntity<byte[]>(data, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<byte[]>(data, HttpStatus.OK);
    }

    @PostMapping("/")
    public String saveToCache(@RequestParam("key") String key, HttpServletRequest body) throws IOException {
        this.cache.put(key, IOUtils.toByteArray(body.getInputStream()));
        return "ok";
    }

    @GetMapping("/invalidate")
    public String deleteFromCache(@RequestParam("key") String key) {
        this.cache.invalidate(key);
        return "ok";
    }
}
