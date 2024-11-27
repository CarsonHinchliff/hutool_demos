package org.example.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.util.StringDesensitizationSerialize;
import org.example.util.DesensitizationTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Carson
 * @create 2024/11/27 星期三 上午 11:49
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = StringDesensitizationSerialize.class)
public @interface StringDesensitization {
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.MY_RULE;
    int startInclude() default 0;
    int endExclude() default 0;
}
