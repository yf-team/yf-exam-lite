package com.yf.exam.core.utils.jackson;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JSON时间格式类，用于兼容不同类型日期
 * @author van
 */
public class MyDateFormat extends SimpleDateFormat {

    private StdDateFormat stdDateFormat = new StdDateFormat();

    public MyDateFormat() {
        // 设置默认的日期格式
        super("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Date parse(String dateStr) throws ParseException {
        if (dateStr != null && !dateStr.contains("T")) {
            return super.parse(dateStr);
        }
        return stdDateFormat.parse(dateStr);
    }

    @Override
    public Object clone() {
        MyDateFormat other = (MyDateFormat)super.clone();
        other.stdDateFormat  = new StdDateFormat();
        return other;
    }

}
