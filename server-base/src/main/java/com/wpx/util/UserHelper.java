package com.wpx.util;

import com.wpx.constant.VerifyConstant;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wpx
 * Created on 2021/2/5 10:11
 */
public class UserHelper {

    public static Integer getUserId() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String userId = request.getHeader(VerifyConstant.USER_ID);
        return Integer.parseInt(userId);
    }

}
