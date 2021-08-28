package com.wpx.util;

/**
 * @author 不会飞的小鹏
 * @desc: 数组工具类
 */
public class ArrayUtils {

    /**
     * 数组为空
     *
     * @param array
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组为空
     *
     * @param array
     */
    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 数组不为空
     *
     * @param array
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 数组不为空
     *
     * @param array
     */
    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

}
