package com.test.cloud.feign;

import com.iminling.core.annotation.EnableResolve;
import com.test.cloud.feign.fallback.BizNewService;
import com.test.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 加上fallback，处理服务提供者宕机时返回对应异常
 * nacos对client名称区分大小写，eureka不区分
 */
@EnableResolve
@FeignClient(value = "provider", contextId = "origin", fallback = BizNewService.class)
public interface IBizService {

    @GetMapping("/hystrix/ok/{id}")
    String ok(@PathVariable("id") String id);

    @GetMapping("/hystrix/time/{id}")
    String time(@PathVariable("id") String id);

    @PostMapping("/hystrix/circuit/{id}")
    String circuit(@PathVariable("id") Integer id);

    @PostMapping("/hystrix/param")
    String param(@RequestParam("name") String name, @RequestParam("age") String age);

    @PostMapping("/hystrix/body")
    String body(@RequestBody User user);

    @PostMapping("/hystrix/circuit/{id}/{aaa}")
    String testParam(@PathVariable("id") Integer id, @PathVariable("aaa")String aaa);

}
