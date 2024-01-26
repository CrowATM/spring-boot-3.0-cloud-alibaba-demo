package cn.zk.oauth2.security.support;


/**
 * @author ZK
 * @date 2024/1/18
 */
public enum LoginTypeEnum {

    PASSWORD("PASSWORD", "密码登陆"),
    PHONE("PHONE", "手机号"),
    EMIL("EMIL", "邮箱")
//    ,
//    THIRD_CODE("THIRD_CODE", "第三方")
    ;

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LoginTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
