package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserService userService;

    @GetMapping("/info")
    public String getInfo(){
        return restTemplate.getForEntity("http://Server-Provider/info", String.class).getBody();
    }

    @GetMapping("user/{id:\\d+}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("user/add")
    public String addUser() {
       return userService.addUser();
    }

    @GetMapping("user/update")
    public void updateUser() {
        userService.updateUser();
    }

    @GetMapping("user/delete/{id:\\d+}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

