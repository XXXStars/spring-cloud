package com.xxx.springcloud.controller;

import com.xxx.springcloud.pojo.Dept;
import com.xxx.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* 提供restful服务
* */
@RestController
public class DeptController {

    @Autowired
    DeptService deptServiceImpl;

    // 获取配置信息得到具体的微服务
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptServiceImpl.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptServiceImpl.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptServiceImpl.queryAll();
    }

    // 注册进来的微服务，获取一些消息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        // 获取列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>service:"+services);

        // 得到具体的微服务信息 通过具体的微服务id
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance :instances){
            System.out.println(instance.getHost() + "\t"+instance.getPort() + "\t" + instance.getUri() + "\t" + instance.getInstanceId());
        }
        return this.discoveryClient;
    }
}
