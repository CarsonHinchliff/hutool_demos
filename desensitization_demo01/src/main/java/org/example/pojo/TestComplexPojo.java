package org.example.pojo;

import lombok.Data;

/**
 * @author Carson
 * @create 2024/11/27 星期三 下午 01:44
 */
@Data
public class TestComplexPojo {
    private String name;
    private Integer age;
    private TestPojo testPojo;
}
