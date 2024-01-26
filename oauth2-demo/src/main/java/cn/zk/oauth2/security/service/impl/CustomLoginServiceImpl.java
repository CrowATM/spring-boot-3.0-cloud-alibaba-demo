package cn.zk.oauth2.security.service.impl;

import cn.zk.oauth2.security.provider.SmsCodeAuthenticationProvider;
import cn.zk.oauth2.security.provider.ThirdCodeAuthenticationProvider;
import cn.zk.oauth2.security.provider.token.EmilCodeAuthenticationToken;
import cn.zk.oauth2.security.service.CustomLoginService;
import org.springframework.stereotype.Service;

/**
 * @author ZK
 * @date 2024/1/19
 */
@Service
public class CustomLoginServiceImpl implements CustomLoginService {
    @Override
    public EmilCodeAuthenticationToken loadUserByEmil(String emil) {
        return null;
    }

    @Override
    public ThirdCodeAuthenticationProvider loadUserByThird() {
        return null;
    }

    @Override
    public SmsCodeAuthenticationProvider loadUserByPhone(String phone) {
        return null;
    }
}
