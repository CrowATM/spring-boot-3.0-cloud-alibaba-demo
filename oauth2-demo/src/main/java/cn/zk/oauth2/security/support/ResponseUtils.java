package cn.zk.oauth2.security.support;

import cn.zk.core.defaul.RespResultCode;
import cn.zk.core.response.CustomRespBody;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.PrintWriter;

/**
 * @author ZK
 * @date 2024/1/30
 */
@Slf4j
public class ResponseUtils {

    public static void write(HttpServletResponse response) {
        write(response, HttpResponseStatus.OK.code(), MediaType.APPLICATION_JSON_VALUE,
                RespResultCode.SUCCESS.getCode(), null, RespResultCode.SUCCESS.getMessage());
    }

    public static <T> void write(HttpServletResponse response, int status, String mediaType,
                                 int code, T data, String message) {
        response.setContentType(mediaType);
        response.setStatus(status);
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSON.toJSONString(new CustomRespBody<>(code, data, message)));
            writer.flush();
        } catch (Exception ex) {
            log.error("response write error: " + ex.getMessage(), ex);
        }
    }
}
