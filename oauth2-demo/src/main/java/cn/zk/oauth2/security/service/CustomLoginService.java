package cn.zk.oauth2.security.service;

import cn.zk.oauth2.security.provider.SmsCodeAuthenticationProvider;
import cn.zk.oauth2.security.provider.ThirdCodeAuthenticationProvider;
import cn.zk.oauth2.security.provider.token.EmilCodeAuthenticationToken;
import jakarta.validation.constraints.NotBlank;

/**
 * @author ZK
 * @date 2024/1/19
 */
public interface CustomLoginService {

    EmilCodeAuthenticationToken loadUserByEmil(@NotBlank String emil);

    ThirdCodeAuthenticationProvider loadUserByThird();

    SmsCodeAuthenticationProvider loadUserByPhone(@NotBlank String phone);
}
