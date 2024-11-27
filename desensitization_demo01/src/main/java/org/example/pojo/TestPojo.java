package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.util.DesensitizationTypeEnum;
import org.example.annotation.StringDesensitization;

import java.math.BigDecimal;

/**
 * @author Carson
 * @create 2024/11/27 星期三 上午 11:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestPojo {
    private String userName;

    @StringDesensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @StringDesensitization(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @StringDesensitization(type = DesensitizationTypeEnum.MY_RULE, startInclude = 0, endExclude = 2)
    private String address;

    @StringDesensitization(type = DesensitizationTypeEnum.PASSWORD)
    private BigDecimal val1;
}
