package cn.zk.web.support;

import cn.zk.core.response.CustomRespBody;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/** 响应参数封装工具
 * @Author zk
 * @CreateDateTime 2022/5/6 13:54
 */
public class ResponseBodyUtil {

    public static Object responseResult(Integer code, Object data, String message){
        if (data instanceof String){
            if (isJsonValid((String) data))
                return "{\"code\":" + code + ",\"data\":" + data + ",\"message\":" + message + "}";
            else
                return "{\"code\":" + code + ",\"data\":\"" + data + "\",\"message\":" + message + "}";
        }
        return new CustomRespBody<>(code, data, message);
    }

    public static boolean isJsonValid(String content){
        try {
            Object parse = JSON.parse(content);
            return parse instanceof JSONArray ||
                    parse instanceof JSONObject;
        } catch (Exception e) {
            return false;
        }
    }
}
