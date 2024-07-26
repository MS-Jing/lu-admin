package com.lj.common_web.utils.validator;

import com.lj.common.exception.CommonException;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/28 11:01
 * hibernate-validator校验工具类
 */
public class ValidatorUtils {

    @Getter
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups) throws CommonException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringJoiner msg = new StringJoiner(",", "[", "]");
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.add(constraint.getMessage());
            }
            throw new CommonException(msg.toString());
        }
    }
}
