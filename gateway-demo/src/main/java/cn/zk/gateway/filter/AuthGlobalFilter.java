package cn.zk.gateway.filter;

import cn.zk.core.defaul.RespResultCode;
import cn.zk.core.response.CustomRespBody;
import cn.zk.gateway.api.CustomAuthService;
import com.alibaba.fastjson2.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ZK
 * @date 2024/1/30
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private static final AntPathMatcher antPathMatcher
            = new AntPathMatcher();

    private final CustomAuthService customAuthService;

    @Autowired
    public AuthGlobalFilter(@Lazy CustomAuthService customAuthService) {
        this.customAuthService = customAuthService;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.warn("AuthGlobalFilter...");
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (antPathMatcher.match("/auth/login", path)) {
            return chain.filter(exchange);
        }
        CompletableFuture<CustomRespBody<String>> future = CompletableFuture.supplyAsync(customAuthService::checkToken);
        CustomRespBody<String> body = future.get(5000, TimeUnit.SECONDS);
        if (RespResultCode.isSuccess(body.getCode())) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        if (StringUtils.isBlank(body.getMessage())) {
            body.setMessage("未登陆");
        }
        byte[] ws = JSON.toJSONString(body).getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(ws);
        return response.writeWith(Mono.just(wrap));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
