package cn.zk.core.response;

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
}
