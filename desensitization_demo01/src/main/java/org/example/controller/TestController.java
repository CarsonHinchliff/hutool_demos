package org.example.controller;

import org.example.pojo.TestComplexPojo;
import org.example.pojo.TestPojo;
import org.example.pojo.TestPojo0;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author Carson
 * @create 2024/11/27 星期三 上午 11:58
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public TestPojo testDesensitization(){
        TestPojo testPojo = new TestPojo();
        testPojo.setVal1(new BigDecimal(100.03));
        testPojo.setUserName("我是用户名");
        testPojo.setAddress("地球中国-北京市通州区京东总部2号楼");
        testPojo.setPhone("13782946666");
        testPojo.setPassword("sunyangwei123123123.");
        System.out.println(testPojo);
        return testPojo;
    }

    @RequestMapping("/test-complex")
    public TestComplexPojo testComplex(){
        TestComplexPojo testComplexPojo = new TestComplexPojo();
        testComplexPojo.setName("Carson");
        testComplexPojo.setAge(18);
        TestPojo testPojo = new TestPojo();
        testPojo.setUserName("我是用户名");
        testPojo.setAddress("地球中国-北京市通州区京东总部2号楼");
        testPojo.setPhone("13782946666");
        testPojo.setPassword("sunyangwei123123123.");
        testComplexPojo.setTestPojo(testPojo);
        return testComplexPojo;
    }

    @RequestMapping("/test0")
    public TestPojo0 testDesensitization0(){
        TestPojo0 testPojo = new TestPojo0();
        testPojo.setUserName("我是用户名");
        testPojo.setAddress("地球中国-北京市通州区京东总部2号楼");
        testPojo.setPhone("13782946666");
        testPojo.setPassword("sunyangwei123123123.");
        System.out.println(testPojo);
        return testPojo;
    }
}
