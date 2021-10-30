package com.xxx.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    // 配置负载均衡 Ribbon注解
    // IRule接口
    // AvailabilityFilteringRule:会先过滤掉跳闸的服务,对剩下的进行轮询
    // 默认为轮询:RoundRobinRule 、 随机:RandomRule 、 RetryRule:会先按照轮询获取服务，如果服务获取失败,则会在指定的时间内,进行重试
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    // 随机
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
