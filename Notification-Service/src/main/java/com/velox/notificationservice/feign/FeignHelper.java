package com.velox.notificationservice.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name ="user-service")
public interface FeignHelper {

    @GetMapping("/user/get")
    public ResponseEntity<String> getDataFromUser();

}
