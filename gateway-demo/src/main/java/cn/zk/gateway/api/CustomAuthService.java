package cn.zk.gateway.api;

import cn.zk.core.response.CustomRespBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZK
 * @date 2024/1/30
 */
@Component
@FeignClient("oauth2-demo")
public interface CustomAuthService {

    @GetMapping("/auth/api/check_token")
    CustomRespBody<String> checkToken();
}
