package cn.zk.oauth2.security.handler;

import cn.zk.core.response.CustomRespBody;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录失败处理逻辑
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {


//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
//
////        httpServletRequest.removeAttribute("_PASSWORD");
//        //返回json数据
//        JsonResult result;
//        if (e instanceof AccountExpiredException) {
//            //账号过期
//            result = JsonResult.fail(ResultCode.USER_ACCOUNT_EXPIRED);
//        } else if(e instanceof CanNotUseException){
//            //账号未启用,密码为空
//            //状态异常需继承AccountStatusException
//            result = JsonResult.fail(ResultCode.CAN_NOT_USE);
//        } else if (e instanceof BadCredentialsException) {
//            //密码错误
//            result = JsonResult.fail(ResultCode.USER_CREDENTIALS_ERROR);
//        } else if (e instanceof CredentialsExpiredException) {
//            //密码过期
//            result = JsonResult.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
//        } else if (e instanceof DisabledException) {
//            //账号不可用
//            result = JsonResult.fail(ResultCode.USER_ACCOUNT_DISABLE);
//        } else if (e instanceof LockedException) {
//            //账号锁定
//            result = JsonResult.fail(ResultCode.USER_ACCOUNT_LOCKED);
//        } else if (e instanceof InternalAuthenticationServiceException) {
//            //用户不存在
//            result = JsonResult.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
//        } else {
//            //其他错误
//            result = JsonResult.fail(ResultCode.COMMON_FAIL);
//        }
//        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        httpServletResponse.getWriter().write(JSON.toJSONString(result));
//    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(new CustomRespBody<String>(1, null, "error"),
                JSONWriter.Feature.PrettyFormat));
    }
}
