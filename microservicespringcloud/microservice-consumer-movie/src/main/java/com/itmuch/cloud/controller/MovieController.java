package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${user.userServicePath}")
  private String userServicePath;
//使用原始硬编码的方式进行访问
//  @GetMapping("/movie/{id}")
//  public User findById(@PathVariable Long id) {
//    return this.restTemplate.getForObject(this.userServicePath + id, User.class);
//  }
  //使用ribbon进行访问，
  //ribbon的工作原理：讲Eureka上面的注册的服务拉取一份在本地，然后根据内部封装的想应算法进行负载均衡访问
  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
  }
}
