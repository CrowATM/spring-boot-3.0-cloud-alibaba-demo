package cn.zk.service.controller;

import cn.zk.core.response.CustomRespBody;
import cn.zk.service.api.Oauth2DemoFeignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZK
 * @date 2024/1/29
 */
@RestController
@RequestMapping("/serviceDemo")
@RequiredArgsConstructor
@Slf4j
public class ServiceDemoController {

    private final Oauth2DemoFeignService oauth2DemoFeignService;

    @GetMapping("/hello")
    public String hello() {
        log.warn("ServiceDemoController hello...");
        return oauth2DemoFeignService.getUserInfo() + "   ServiceDemoController hello...";
    }

}
