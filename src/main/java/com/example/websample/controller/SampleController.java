package com.example.websample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
//lombok 사용 편의성을 높이기 위해 사용함
@RestController
public class SampleController {

    //외부 url을 찾아올수 있도록 하기 위해
//    @RequestMapping(value = "/order/1", method = RequestMethod.GET)
    @GetMapping("/order/1")
    public  String getOrder() {
        log.info("Get some order");
        return "OrderId:1, orderAmount:1000";
    }

    @PostMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }
}
