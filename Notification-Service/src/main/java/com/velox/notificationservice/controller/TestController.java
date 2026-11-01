package com.velox.notificationservice.controller;

import com.velox.notificationservice.feign.FeignHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class TestController {

    @Autowired
    private FeignHelper helper;

    @GetMapping("/get")
    public ResponseEntity<String> getData() {
        return new ResponseEntity<>(helper.getDataFromUser().getBody(), HttpStatus.OK);
    }
}
