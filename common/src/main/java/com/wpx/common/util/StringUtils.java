package com.wpx.common.util;

/**
 * @author 不会飞的小鹏
 * create on 2021/5/31 22:40
 */
public class StringUtils {

    public static final String SPACE = " ";

    public static final String ZERO = "0";

    public static final String MINUS_ONE = "-1";

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean nonEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean startWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return startWith(str, prefix, true);
    }

    public static boolean startWith(CharSequence str, CharSequence prefix, boolean ignoreCase) {
        return startWith(str, prefix, ignoreCase, false);
    }

    public static boolean startWith(CharSequence str, CharSequence prefix, boolean ignoreCase, boolean ignoreEquals) {
        if (null != str && null != prefix) {
            boolean isStartWith;
            if (ignoreCase) {
                isStartWith = str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
            } else {
                isStartWith = str.toString().startsWith(prefix.toString());
            }

            if (!isStartWith) {
                return false;
            } else {
                return !ignoreEquals || !equals(str, prefix, ignoreCase);
            }
        } else if (!ignoreEquals) {
            return false;
        } else {
            return null == str && null == prefix;
        }
    }

    /**
     * 比较两个字符串（大小写敏感）。
     *
     * <pre>
     * equals(null, null)   = true
     * equals(null, &quot;abc&quot;)  = false
     * equals(&quot;abc&quot;, null)  = false
     * equals(&quot;abc&quot;, &quot;abc&quot;) = true
     * equals(&quot;abc&quot;, &quot;ABC&quot;) = false
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是{@code null}，则返回{@code true}
     */
    public static boolean equals(CharSequence str1, CharSequence str2) {
        return equals(str1, str2, false);
    }

    /**
     * 比较两个字符串是否相等。
     *
     * @param str1       要比较的字符串1
     * @param str2       要比较的字符串2
     * @param ignoreCase 是否忽略大小写
     * @return 如果两个字符串相同，或者都是{@code null}，则返回{@code true}
     * @since 3.2.0
     */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为null才判断相等
            return str2 == null;
        }
        if (null == str2) {
            // 字符串2空，字符串1非空，直接false
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.toString().contentEquals(str2);
        }
    }

}
