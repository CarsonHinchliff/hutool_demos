package org.example.utils;

import java.lang.reflect.Field;

/**
 * @author Carson
 * @create 2024/11/27 星期三 下午 03:59
 */
public class ClassRecursionUtils {
    public static Class<?> getClass(Class<?> c, String fieldName) {
        if (c !=null && !hasField(c, fieldName)) {
            return getClass(c.getSuperclass(), fieldName);
        }
        return c;
    }

    public static boolean hasField(Class<?> c, String fieldName){
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            if (fieldName.equals(f.getName())) {
                return true;
            }
        }
        return false;
    }
}