package com.example.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Apprentice系统Util
 */
public class ApprenticeUtil {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    public static void main(String[] args) {
        String str = humpToLine("tabAdmin, tabProductStop");
        System.out.println(str);
        System.out.println(lineToHump(str));
    }

    /**
     * 驼峰转下划线
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 获取QueryWrapper
     */
    public static <E> QueryWrapper<E> getQueryWrapper(E entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        QueryWrapper<E> eQueryWrapper = new QueryWrapper<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            // 忽略final字段
            if (Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            // 打破封装
            field.setAccessible(true);
            try {
                Object obj = field.get(entity);
                if (!ObjectUtils.isEmpty(obj)) {
                    String name = ApprenticeUtil.humpToLine(field.getName());
                    eQueryWrapper.eq(name, obj);
                }
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return eQueryWrapper;

    }

    /** 反射获取字段值
     */
    public static <E> Object getValueForClass(E entity, String value) {

        Field id = null;
        PropertyDescriptor pd = null;
        try {
            id = entity.getClass().getDeclaredField(value);
            pd = new PropertyDescriptor(id.getName(), entity.getClass());
        } catch (NoSuchFieldException | IntrospectionException e) {
            e.printStackTrace();
        }
        // 获取get方法
        Method getMethod = Objects.requireNonNull(pd).getReadMethod();
        return ReflectionUtils.invokeMethod(getMethod, entity);
    }
}