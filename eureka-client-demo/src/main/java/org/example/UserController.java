package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserController {


    @GetMapping("user/{id:\\d+}")
    public User get(@PathVariable Long id) {
        log.info("获取用户 id 为：{} 的信息", id);
        return new User(id, "mybird", "123456");
    }

    @GetMapping("user")
    public List<User> list() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "mybird", "123456"));
        list.add(new User(2L, "scott", "12343543"));
        log.info("获取用户信息：{}", list);
        return list;

    }

    @PostMapping("user")
    public void add(@RequestBody User user) {
        log.info("新增用户成功: {}", user);
    }

    @PutMapping("user")
    public void update(@RequestBody User user) {
        log.info("更新用户成功：{}", user);
    }

    @DeleteMapping("user/{id:\\d+}")
    public void detele(@PathVariable Long id) {
        log.info("删除用户成功，id：{}", id);
    }

}
