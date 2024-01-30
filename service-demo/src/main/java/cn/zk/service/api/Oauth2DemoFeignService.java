package cn.zk.service.api;

import cn.zk.core.response.CustomRespBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZK
 * @date 2024/1/29
 */
@Component
@FeignClient("oauth2-demo")
public interface Oauth2DemoFeignService {
    @GetMapping("/auth/api/getUserInfo")
    String getUserInfo();

    @GetMapping("/auth/api/checkToken")
    CustomRespBody<String> checkToken();
}
