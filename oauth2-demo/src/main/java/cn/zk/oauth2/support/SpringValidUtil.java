package cn.zk.oauth2.support;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.ValidatorFactory;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Description 手动校验
 * @Author ZK
 * @Date 2023/5/6 11:20
 */
public class SpringValidUtil {

    public static <T> void myValid(T obj, Class<?>... classes) throws ValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<T>> validate = validatorFactory.getValidator().validate(obj, classes);
        if(!CollectionUtils.isEmpty(validate)) {
            String message = validate.stream().map(a -> a.getPropertyPath() + ":" +
                            a.getMessage()).reduce((m1, m2) -> m1 + ";" + m2)
                    .orElse("参数校验失败!");
            throw new ValidationException(message);
        }
    }
}
