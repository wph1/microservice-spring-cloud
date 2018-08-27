package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

  @Autowired
  private LoadBalancerClient loadBalancerClient;
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

  /**
   * 测试自定义配置Ribbon客户端
   * microservice-provider-user 服务使用的是我们自己配置的负载均衡规则，随机
   * microservice-provider-user2 服务使用的是Ribbon客户端默认的负载均衡规则（轮询）
   * @return
   */
  @GetMapping("/test")
  public String test() {
    //choose()方法选择服务
    ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
    System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-provider-user2");
    System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

    return "1";
  }
}
