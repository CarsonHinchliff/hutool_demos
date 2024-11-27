package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author Carson
 * @create 2024/11/27 星期三 下午 03:54
 */
public class DataMaskKey implements Serializable {
    //不进行序列化,设置key 来进行过滤的把控，默认不开启
    private transient Boolean isPrivacyKey = false;

    @JsonIgnore
    public Boolean getPrivacyKey() {
        return isPrivacyKey;
    }

    public void setPrivacyKey(Boolean privacyKey) {
        isPrivacyKey = privacyKey;
    }
}
