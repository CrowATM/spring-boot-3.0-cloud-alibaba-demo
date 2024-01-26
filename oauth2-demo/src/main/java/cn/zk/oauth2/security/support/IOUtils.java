package cn.zk.oauth2.security.support;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author ZK
 * @date 2024/1/18
 */
public class IOUtils {

    public static <T> T getTByRequest(HttpServletRequest request, Class<T> clazz) throws IOException {
        if (request == null || clazz == null) {
            throw new IllegalArgumentException("request and clazz not allow null");
        }
        try (InputStream inputStream = request.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder builder = new StringBuilder();
            String line;
            // 读取请求体中的数据
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            // 将请求体数据转换为对象
            return JSON.parseObject(builder.toString(), clazz);
        } catch (IOException e) {
            throw new IOException("请求转换json参数到对象失败!" + e.getMessage(), e);
        }
    }
}
