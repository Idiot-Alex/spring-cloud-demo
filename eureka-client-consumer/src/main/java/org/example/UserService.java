package org.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    @Resource
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "getUserDefault")
    public User getUser(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        URI uri = UriComponentsBuilder.fromUriString("http://Server-Provider/user/{id}")
                .build().expand(params).encode().toUri();
        return restTemplate.getForObject(uri, User.class);
    }

    public User getUserDefault(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("defaultUser");
        user.setPassword("123455");
        return user;
    }

    @GetMapping("user")
    public List<User> getUsers() {
        return restTemplate.getForObject("http://Server-Provider/user", List.class);
    }

    public String addUser() {
        User user = new User(1L, "mybird", "123467");
        HttpStatus status = restTemplate.postForEntity("http://Server-Provider/user", user, null).getStatusCode();
        if (status.is2xxSuccessful()) {
            return "新增用户成功";
        } else {
            return "新增用户失败";
        }
    }

    public void updateUser() {
        User user = new User(1L, "mybird", "123456");
        restTemplate.put("http://Server-Provider/user", user);
    }

    public void deleteUser(Long id) {
        restTemplate.delete("http://Server-Provider/user/{id}", id);
    }
}
