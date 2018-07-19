package com.maoliicai.common.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by lauyk on 2015/11/17.
 * 数字转换辅助类
 */
public class NumberConvert {
    /**
     *
     * @Description: 对象转化为int类型
     * @param value
     * @param defaultValue
     * @return integer
     * @throws
     */
    public final static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     *
     * @Description: 对象转化为float类型
     * @param value
     * @param defaultValue
     * @return float
     * @throws
     */
    public final static float convertToFloat(Object value, float defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Float.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     *
     * @Description: 对象转化为float类型
     * @param value
     * @param defaultValue
     * @return float
     * @throws
     */
    public final static double convertToDouble(Object value, Double defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }


    /**
     *
     * @Description: 对象转化为long类型
     * @param value
     * @param defaultValue
     * @return long
     * @throws
     */
    public final static long convertToLong(Object value, Long defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            Logger.i(String.valueOf(defaultValue));
            return defaultValue;
        }
        try {
            return Long.valueOf(value.toString());
        } catch (Exception e) {
            Logger.i(e.toString());
            return defaultValue;
        }
    }
}
