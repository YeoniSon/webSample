package com.example.websample.controller;

import com.example.websample.dto.ErrorResponse;
import com.example.websample.exception.ErrorCode;
import com.example.websample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
//lombok 사용 편의성을 높이기 위해 사용함
@RestController
public class SampleController {

    //외부 url을 찾아올수 있도록 하기 위해
//    @RequestMapping(value = "/order/1", method = RequestMethod.GET)
    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        log.info("Get some order : " + id);

        if ("500".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR,
                    "500 is too big orderId");
        }

        if ("3".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR,
                    "3 is too small orderId");
        }

        if ("4".equals(id)) {
            throw new SQLIntegrityConstraintViolationException(
                    "Duplicated insertion was tried."
            );
        }
        return "OrderId:" + id + ", orderAmount:1000";
    }



    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete some order : " + id);
        return "Delete orderId:" + id;
    }

    // 파라미터로 이용해서 가져올수 있음 -> 게시판 같은 경우에 많이 사용(페이지, 서치키워드)
    @GetMapping("/order")
    public String getOrderWithParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,
            @RequestParam("orderAmount") Integer amount) {
        log.info("Get some order : " + id + ", amount : " + amount);
        return "OrderId:" + id + ", orderAmount:" + amount;
    }

    //RequsetBody 사용 -> 큰 데이터 사용시 사용한다.
    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("Get some order : " + createOrderRequest +
                ", userAccountId : " + userAccountId);
        return "OrderId:" + createOrderRequest.getOrderId() + ", orderAmount:"
                + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orderAmount:1000";
    }


    @Data //lombok을 이용한것
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;

    }

}
