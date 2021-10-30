package com.xxx.springcloud.consumerController;

import com.xxx.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class DeptConsumerController {

    // 消费者 没有service层
    // RestTemplate 供我们直接调用就可以了 注册spring
    // (url , 实体 ; Map ,class<T> responseType)
    @Autowired
    RestTemplate restTemplate;  // 提供多种便捷访问远程http服务的方法,简单的restful服务模板

    // Ribbon 地址应该是一个变量 通过服务名访问
    // private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";

    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/add",Boolean.class,dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id,Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }
}
