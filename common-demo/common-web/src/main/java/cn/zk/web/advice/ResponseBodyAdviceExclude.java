package cn.zk.web.advice;

import java.lang.annotation.*;

/** ResponseBodyAdvice中排除的接口
 * @Author zk
 * @Data 2022/3/30 18:26
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBodyAdviceExclude {
}
