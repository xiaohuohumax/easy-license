package top.xiaohuohu.license.core.utils;

import top.xiaohuohu.license.core.entitys.LicenseData;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> List<String> validation(T licenseData) {
        Set<ConstraintViolation<T>> validate = validator.validate(licenseData);
        return validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(validation(new LicenseData()));
    }
}
