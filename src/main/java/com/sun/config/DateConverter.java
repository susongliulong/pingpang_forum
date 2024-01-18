package com.sun.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import java.time.LocalDate;

/**
 * @description 日期转换
 */
public class DateConverter implements Converter<String, LocalDate> {
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String dateFormata = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";
    private static final String shortDateFormata = "yyyy/MM/dd";
    private static final String timeStampFormat = "^\\d+$";

    @Override
    public LocalDate convert(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        value = value.trim();
        try {
            if (value.contains("-")) {
                if (value.contains(":")) {
                    //yyyy-MM-dd HH:mm:ss 格式
                    return LocalDate.parse(value.substring(0, 10));
                } else {
                    //yyyy-MM-dd 格式
                    return LocalDate.parse(value);
                }
            } else if (value.matches(timeStampFormat)) {
                //时间戳
                Long lDate = new Long(value);
                return LocalDate.ofEpochDay(lDate);
            } else if (value.contains("/")) {
                String[] strings = value.split("//");
                return LocalDate.parse(strings[0] + "-" + strings[1] +"-"+ strings[2]);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", value));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", value));
    }
}

