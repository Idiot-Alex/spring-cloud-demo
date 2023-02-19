package org.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("Server-Provider")
public interface UserService {

    @GetMapping("user/{id}")
    User get(@PathVariable("id") Long id);

    @GetMapping("user")
    List<User> get();

    @PostMapping("user")
    void add(@RequestBody User user);

    @PutMapping("user")
    void update(@RequestBody User user);

    @DeleteMapping("user/{id}")
    void delete(@PathVariable("id") Long id);
}
