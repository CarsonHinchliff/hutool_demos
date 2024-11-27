package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.annotation.PrivacyEncrypt;
import org.example.enums.PrivacyTypeEnum;

import java.math.BigDecimal;

/**
 * @author Carson
 * @created 2024/11/27 星期三 下午 04:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentModel extends DataMaskKey{
    private String name;
    private int age;
    private BigDecimal salary;

    @PrivacyEncrypt(type= PrivacyTypeEnum.ADDRESS)
    private String address;
}
