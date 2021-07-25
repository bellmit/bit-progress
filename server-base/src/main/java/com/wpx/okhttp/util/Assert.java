package com.wpx.okhttp.util;

import com.wpx.exception.CustomizeException;
import com.wpx.exception.IExceptionMessage;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * @author 不会飞的小鹏
 * create on 2021/6/27 2:09
 * @Description 断言类
 */
public class Assert extends org.springframework.util.Assert {

    /**
     * 判断是否为真，不为真则直接抛出异常
     *
     * @param expression
     * @param exception
     */
    public static void isTrue(boolean expression, IExceptionMessage exception) {
        isTrue(expression, CustomizeException.error(exception));
    }

    /**
     * 判断是否为真，不为真则直接抛出异常
     *
     * @param expression
     * @param exception
     */
    public static void isTrue(boolean expression, CustomizeException exception) {
        if (!expression) {
            throw exception;
        }
    }

    /**
     * 判断是否为null，不为null则抛出异常
     *
     * @param object
     * @param exception
     */
    public static void isNull(@Nullable Object object, IExceptionMessage exception) {
        isNull(object, CustomizeException.error(exception));
    }

    /**
     * 判断是否为null，不为null则抛出异常
     *
     * @param object
     * @param exception
     */
    public static void isNull(@Nullable Object object, CustomizeException exception) {
        if (object != null) {
            throw exception;
        }
    }

    /**
     * 判断是否不为null，为null则抛出异常
     *
     * @param object
     * @param exception
     */
    public static void notNull(@Nullable Object object, IExceptionMessage exception) {
        notNull(object, CustomizeException.error(exception));
    }

    /**
     * 判断是否不为null，为null则抛出异常
     *
     * @param object
     * @param exception
     */
    public static void notNull(@Nullable Object object, CustomizeException exception) {
        if (object == null) {
            throw exception;
        }
    }

    /**
     * 判断是否为空字符串，不为空字符串则抛出异常
     *
     * @param str
     * @param exception
     */
    public static void isEmpty(String str, IExceptionMessage exception) {
        isEmpty(str, CustomizeException.error(exception));
    }

    /**
     * 判断是否为空字符串，不为空字符串则抛出异常
     *
     * @param str
     * @param exception
     */
    public static void isEmpty(String str, CustomizeException exception) {
        if (str != null && !str.isEmpty()) {
            throw exception;
        }
    }

    /**
     * 判断是否不为空字符串，为空字符串则抛出异常
     *
     * @param str
     * @param exception
     */
    public static void isNotEmpty(String str, IExceptionMessage exception) {
        isNotEmpty(str, CustomizeException.error(exception));
    }

    /**
     * 判断是否不为空字符串，为空字符串则抛出异常
     *
     * @param str
     * @param exception
     */
    public static void isNotEmpty(String str, CustomizeException exception) {
        if (str == null || str.isEmpty()) {
            throw exception;
        }
    }

    /**
     * 判断是否为空集合，不为空集合则抛出异常
     *
     * @param collection
     * @param exception
     */
    public static void isEmpty(Collection collection, IExceptionMessage exception) {
        isEmpty(collection, CustomizeException.error(exception));
    }

    /**
     * 判断是否为空集合，不为空集合则抛出异常
     *
     * @param collection
     * @param exception
     */
    public static void isEmpty(Collection collection, CustomizeException exception) {
        if (collection != null && !collection.isEmpty()) {
            throw exception;
        }
    }

    /**
     * 判断是否不为空集合，为空集合则抛出异常
     *
     * @param collection
     * @param exception
     */
    public static void isNotEmpty(Collection collection, IExceptionMessage exception) {
        isNotEmpty(collection, CustomizeException.error(exception));
    }

    /**
     * 判断是否不为空集合，为空集合则抛出异常
     *
     * @param collection
     * @param exception
     */
    public static void isNotEmpty(Collection collection, CustomizeException exception) {
        if (collection == null || collection.isEmpty()) {
            throw exception;
        }
    }

}
