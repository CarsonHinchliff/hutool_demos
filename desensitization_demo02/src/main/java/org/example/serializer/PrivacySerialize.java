package org.example.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.apache.commons.lang3.StringUtils;
import org.example.enums.PrivacyTypeEnum;
import org.example.annotation.PrivacyEncrypt;
import org.example.utils.ClassRecursionUtils;
import org.example.utils.DesensitizedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Carson
 * @create 2024/11/27 星期三 下午 03:54
 */
public class PrivacySerialize extends JsonSerializer<String> implements ContextualSerializer {
    public static final Logger logger = LoggerFactory.getLogger(PrivacySerialize.class);
    private PrivacyTypeEnum type;

    private Integer prefixNoMaskLen;

    private Integer suffixNoMaskLen;

    private String maskStr;

    public PrivacySerialize(PrivacyTypeEnum type, Integer prefixNoMaskLen, Integer suffixNoMaskLen, String maskStr) {
        this.type = type;
        this.prefixNoMaskLen = prefixNoMaskLen;
        this.suffixNoMaskLen = suffixNoMaskLen;
        this.maskStr = maskStr;
    }
    public PrivacySerialize() {
    }

    @Override
    public void serialize(String origin, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        boolean flag = false;
        //反射获取对象
        Object currentValue = jsonGenerator.getOutputContext().getCurrentValue();
        //反射获取class
        Class<?> aClass = jsonGenerator.getOutputContext().getCurrentValue().getClass();
        List<Field> fieldList = getFieldList(aClass);
        for (Field field : fieldList) {
            //开始反射获取
            String name = field.getName();
            if ("isPrivacyKey".equals(name)){
                try {
                    //进行重新赋值
                    flag = (boolean) field.get(currentValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //反射进行 进行开关判定
        if (flag){
            //logger.info("进行脱敏处理");
            if (StringUtils.isNotBlank(origin) && null != type) {
                switch (type) {
                    case CHINESE_NAME:
                        jsonGenerator.writeString(DesensitizedUtils.chineseName(origin));
                        break;
                    case ID_CARD:
                        jsonGenerator.writeString(DesensitizedUtils.idCardNum(origin));
                        break;
                    case FIXED_PHONE:
                        jsonGenerator.writeString(DesensitizedUtils.fixedPhone(origin));
                        break;
                    case MOBILE_PHONE:
                        jsonGenerator.writeString(DesensitizedUtils.mobilePhone(origin));
                        break;
                    case ADDRESS:
                        jsonGenerator.writeString(DesensitizedUtils.address(origin));
                        break;
                    case EMAIL:
                        jsonGenerator.writeString(DesensitizedUtils.email(origin));
                        break;
                    case BANK_CARD:
                        jsonGenerator.writeString(DesensitizedUtils.bankCard(origin));
                        break;
                    case PASSWORD:
                        jsonGenerator.writeString(DesensitizedUtils.password(origin));
                        break;
                    case KEY:
                        jsonGenerator.writeString(DesensitizedUtils.key(origin));
                        break;
                    case CUSTOMER:
                        jsonGenerator.writeString(DesensitizedUtils.desValue(origin, prefixNoMaskLen, suffixNoMaskLen, maskStr));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknow sensitive type enum " + type);
                }
            }else {
                jsonGenerator.writeString("");
            }
        }else {
            //logger.info("不进行脱敏处理");
            jsonGenerator.writeString(origin);
        }

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                PrivacyEncrypt encrypt = beanProperty.getAnnotation(PrivacyEncrypt.class);
                if (encrypt == null) {
                    encrypt = beanProperty.getContextAnnotation(PrivacyEncrypt.class);
                }
                if (encrypt != null) {
                    return new PrivacySerialize(encrypt.type(), encrypt.prefixNoMaskLen(),
                            encrypt.suffixNoMaskLen(), encrypt.maskStr());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }

    private List<Field> getFieldList(Class<?> clazz){
        if(null == clazz){
            return null;
        }
        List<Field> fieldList = new ArrayList<>();
        //递归查找 需求 的字段
        Class<?> aClass = ClassRecursionUtils.getClass(clazz, "isPrivacyKey");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field != null){
                //设置属性的可访问性
                field.setAccessible(true);
                //过滤静态
                if(Modifier.isStatic(field.getModifiers())){
                    continue;
                }
                String name = field.getName();
                //过滤非布尔类型
                Class<?> type = field.getType();
                //并且只添加 isPrivacyKey
                if (type.isAssignableFrom(Boolean.class) && "isPrivacyKey".equals(name)){
                    fieldList.add(field);
                }
            }
        }
        return fieldList;
    }

}
