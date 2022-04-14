package top.xiaohuohu.license.core.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 参数校验工具
 */
public class ValidationUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 对象参数校验
     *
     * @param data 待检验对象
     * @return 空: 参数校验通过 ; 非空: 参数错误提示
     */
    public static <T> List<String> validation(T data) {
        Set<ConstraintViolation<T>> validate = validator.validate(data);
        return validate.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}
