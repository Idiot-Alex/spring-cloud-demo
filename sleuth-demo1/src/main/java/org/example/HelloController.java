package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("hello")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private RestTemplate restTemplate;

    @GetMapping
    public String hello() {
        logger.info("调用 sleuth-provider1 的 hello 接口");
        return restTemplate.getForEntity("http://localhost:10002/hello", String.class).getBody();
    }
}
