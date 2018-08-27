package com.itmuch.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wph
 * @date 2018/8/27 14:42
 * 定义单个Ribbon请求客户端的配置
 * 注意：这个配置文件不能放在springboot的扫描的包下，会覆盖所有的Ribbon客户端使用这个配置
 * 所以单独使用了一个config包（springboot默认的扫描范围是main函数方法所在的包以及子包）
 *
 *
 * 注意：这仅仅是一种方式，放到main方法加载的路径外边（所以这个对象只有在访问的时候，才会被创建放入到容器里面）
 * 另一种方式：放在main方法的扫描路径下，可以通过相应的配置，不让其覆盖所有的Ribbon客户端（）
 */
@Configuration
public class TestConfiguration {

    @Bean
    public IRule ribbonRule() {
        //单个Ribbon的负载均衡策略是随机
        return new RandomRule();
    }
}
