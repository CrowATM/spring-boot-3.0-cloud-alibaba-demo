package cn.zk.oauth2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZK
 * @date 2024/1/17
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/demo")
public class Oauth2DemoController {

    @GetMapping
    public String hello() {
        return "hello";
    }
}
