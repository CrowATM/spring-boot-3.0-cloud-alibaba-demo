package cn.zk.web.advice;

import cn.zk.core.response.CustomRespBody;
import cn.zk.web.support.ResponseBodyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/** 全局响应格式
 * @author ZK
 * @date 2024/1/31
 */
@Slf4j
@RestControllerAdvice
public class CustomBaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = returnType.getMethod();
        if (method == null) return false;
        //有ResponseBodyAdviceExclude注解时不处理
        AnnotatedElement annotatedElement = returnType.getAnnotatedElement();
        ResponseBodyAdviceExclude responseAnnotation = AnnotationUtils.findAnnotation(annotatedElement, ResponseBodyAdviceExclude.class);
        if (responseAnnotation != null) return false;
        Class<?> parameterType = returnType.getParameterType();
        //返回String处理
        if (parameterType.isAssignableFrom(String.class)) return true;
        //返回ResponseResult时不处理
        else return !parameterType.isAssignableFrom(CustomRespBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().set("Content-Type", "application/json");
        return ResponseBodyUtil.responseResult(0, body, null);
    }
}
