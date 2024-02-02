package cn.zk.core.defaul;

import java.util.Arrays;

/**
 * 返回码定义
 * 规定:
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 */
public enum RespResultCode {
    /* 成功 */
    SUCCESS(200, "SUCCESS"),

    /* 默认失败 */
    COMMON_FAIL(500, "FAIL"),

    /* 内部错误 501 ~ 999 */

    /* 参数错误：1000～1999 */
//    PARAM_NOT_VALID(1001, "参数无效"),
//    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数校验错误"),
//    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    CAN_NOT_USE(2010, "账户未启用(密码为空)"),


    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限");

    private Integer code;
    private String message;

    RespResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        return Arrays.stream(values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .map(RespResultCode::getMessage)
                .orElseThrow(() -> new IllegalArgumentException("无法通过[" + code + "]获取返回码解释!"));
    }

    /**
     * 是否是成功
     * @param code
     * @return
     */
    public static boolean isSuccess(Integer code) {
        if (code == null) {
            throw new IllegalArgumentException("code can't be null!");
        }
        return code.equals(SUCCESS.code);
    }
}
