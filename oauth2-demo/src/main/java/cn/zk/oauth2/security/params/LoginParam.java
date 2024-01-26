package cn.zk.oauth2.security.params;

import cn.zk.oauth2.security.support.LoginTypeValid;
import lombok.Data;

/**
 * @author ZK
 * @date 2024/1/18
 */
@Data
@LoginTypeValid
public class LoginParam {
    /**
     * 登陆方式
     */
    private String loginType;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String emil;
    /**
     * 验证码
     */
    private String code;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 第三方
     */
    private String third;
}
