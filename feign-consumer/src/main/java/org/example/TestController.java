package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private UserService userService;


    @GetMapping("user/{id:\\d+}")
    public User getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("user")
    public List<User> getUsers() {
        return userService.get();
    }

    @GetMapping("user/add")
    public void addUser() {
        userService.add(new User(1L, "add-feign", "234567"));
    }

    @GetMapping("user/update")
    public void updateUser() {
        userService.update(new User(1L, "test-feign", "134567"));
    }

    @GetMapping("user/delete/{id:\\d+}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
