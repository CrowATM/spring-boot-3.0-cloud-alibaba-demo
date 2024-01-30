package cn.zk.oauth2.controller;

import cn.zk.core.defaul.DefaultFiled;
import cn.zk.core.defaul.RespResultCode;
import cn.zk.core.response.CustomRespBody;
import cn.zk.oauth2.security.support.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZK
 * @date 2024/1/17
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/api")
public class Oauth2DemoController {

    private final HttpServletRequest request;

    @GetMapping("/getUserInfo")
    public String getUserInfo() {
        log.warn("Oauth2DemoController getUserInfo...");
        return "Oauth2DemoController getUserInfo";
    }

    @GetMapping("/checkToken")
    public CustomRespBody<String> checkToken() {
        log.warn("Oauth2DemoController checkToken...");
        String accessToken = request.getHeader(DefaultFiled.ACCESS_TOKEN_HEADER_NAME);
        String refreshToken = request.getHeader(DefaultFiled.REFRESH_TOKEN_HEADER_NAME);
        return new CustomRespBody<>(TokenUtils.checkToken(accessToken, refreshToken) ?
                RespResultCode.SUCCESS : RespResultCode.COMMON_FAIL);
    }
}
