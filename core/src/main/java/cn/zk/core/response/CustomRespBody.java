package cn.zk.core.response;

import cn.zk.core.defaul.RespResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZK
 * @date 2024/1/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomRespBody<T> {

    private int code;

    private T data;

    private String message;

    public CustomRespBody(RespResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public CustomRespBody(RespResultCode resultCode, T t) {
        this.code = resultCode.getCode();
        this.data = t;
        this.message = resultCode.getMessage();
    }

    public CustomRespBody(RespResultCode resultCode, T t, String message) {
        this.code = resultCode.getCode();
        this.data = t;
        this.message = message;
    }
}
