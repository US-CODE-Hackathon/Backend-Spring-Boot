package com.garliccastle.demo.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        // 아무것도 하지 않음
    }
}
