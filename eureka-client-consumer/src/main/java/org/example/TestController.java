package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/info")
    public String getInfo(){
        return restTemplate.getForEntity("http://Server-Provider/info", String.class).getBody();
    }
}
