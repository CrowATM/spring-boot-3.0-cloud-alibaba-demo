package cn.zk.oauth2.security.support;

import cn.zk.oauth2.security.params.LoginParam;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Constraint(
        validatedBy = {LoginTypeValid.LoginTypeValidImpl.class}
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginTypeValid {

    String message() default "登陆参数异常!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class LoginTypeValidImpl implements ConstraintValidator<LoginTypeValid, LoginParam> {

        @Override
        public boolean isValid(LoginParam loginParam, ConstraintValidatorContext constraintValidatorContext) {

            LoginTypeEnum loginTypeEnum;
            try {
                loginTypeEnum = LoginTypeEnum.valueOf(loginParam.getLoginType());
            } catch (NullPointerException ex) {
                setConstraintViolation(constraintValidatorContext, "登陆方式不能为空!");
                return false;
            } catch (IllegalArgumentException ex) {
                setConstraintViolation(constraintValidatorContext, "非预设登陆方式!");
                return false;
            }
            switch (loginTypeEnum) {
                case PASSWORD -> {
                    if (StringUtils.isAnyBlank(loginParam.getUsername(), loginParam.getPassword())) {
                        setConstraintViolation(constraintValidatorContext, "用户名和密码不能为空!");
                        return false;
                    }
                }
                case EMIL -> {
                    if (StringUtils.isAnyBlank(loginParam.getEmil(), loginParam.getCode())) {
                        setConstraintViolation(constraintValidatorContext, "邮箱和验证码不能为空!");
                        return false;
                    }
                }
                case PHONE -> {
                    if (StringUtils.isAnyBlank(loginParam.getPhone(), loginParam.getCode())) {
                        setConstraintViolation(constraintValidatorContext, "手机号和验证码不能为空!");
                        return false;
                    }
                }
//                case THIRD_CODE -> {
//                    if (StringUtils.isAnyBlank(loginParam.getThird(), loginParam.getCode())) {
//                        setConstraintViolation(constraintValidatorContext, "第三方和验证码不能为空!");
//                        return false;
//                    }
//                }
            }
            return true;
        }

        private void setConstraintViolation(ConstraintValidatorContext constraintValidatorContext, String message) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }
    }
}
