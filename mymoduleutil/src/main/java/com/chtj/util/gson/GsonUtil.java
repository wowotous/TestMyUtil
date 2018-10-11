package com.chtj.util.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * GSON处理工具类
 */

public class GsonUtil<T> {

    /**
     * 获取json转换后的对象
     * @param jsonStr json字符串
     * @return 对象
     */
    public static <T> T  getBean(String jsonStr,Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, clazz);
    }

    /**
     * 获取json转换后的集合
     * @param json json字符串
     * @param clazz 实体类对象
     * @param <T> 泛型
     * @return 泛型集合
     */
    public static <T> List<T> jsonToArray(String json, Class<T[]> clazz)
    {
        Gson gson = new Gson();
        T[] array = gson.fromJson(json, clazz);
        return Arrays.asList(array);
    }
}
