package com.itmuch.cloud;

import com.itmuch.config.TestConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//将服务注册到Eureka上面
@EnableEurekaClient
//自定义配置Ribbon客户端的负载均衡策略
//name是使用的是注册到Eureka上的服务名称，configuration是我们自己定义的配置类（随机）
@RibbonClient(name = "microservice-provider-user", configuration = TestConfiguration.class)
public class MicroserviceSimpleConsumerMovieApplication {
	@Bean
	@LoadBalanced//引入Ribbon，ribbon主要就是访问eureka上面注册的服务，并且有负载均衡的功能，默认采用轮询的机制（，属于客户端的服务均衡，nginx属于服务的的负载均衡）
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimpleConsumerMovieApplication.class, args);
	}
}
