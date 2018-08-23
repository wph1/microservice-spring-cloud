package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EurekaClient eurekaClient;
//
//  @Autowired
//  private DiscoveryClient discoveryClient;

  @GetMapping("/simple/{id}")
  public User findById(@PathVariable Long id) {
    return this.userRepository.findOne(id);
  }

  @GetMapping("/eureka-instance")
  public String serviceUrl() {//MICROSERVICE-PROVIDER-USER  是在eureka上面显示的服务名称信息
    InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
    return instance.getHomePageUrl();//返回的信息：http://192.168.5.32:7900/
  }
//
//  @GetMapping("/instance-info")
//  public ServiceInstance showInfo() {
//    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
//    return localServiceInstance;
//  }
//
//  @PostMapping("/user")
//  public User postUser(@RequestBody User user) {
//    return user;
//  }
//
//  // 该请求不会成功
//  @GetMapping("/get-user")
//  public User getUser(User user) {
//    return user;
//  }
//
//  @GetMapping("list-all")
//  public List<User> listAll() {
//    ArrayList<User> list = Lists.newArrayList();
//    User user = new User(1L, "zhangsan");
//    User user2 = new User(2L, "zhangsan");
//    User user3 = new User(3L, "zhangsan");
//    list.add(user);
//    list.add(user2);
//    list.add(user3);
//    return list;
//
//  }
}
