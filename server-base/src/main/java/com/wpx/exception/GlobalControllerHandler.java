package com.wpx.exception;

import com.wpx.common.constant.VerifyConstant;
import com.wpx.common.exception.CustomizeException;
import com.wpx.common.exception.ExceptionMessage;
import com.wpx.common.exception.ValidationException;
import com.wpx.common.util.StringUtils;
import com.wpx.model.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Objects;

@RestControllerAdvice
public class GlobalControllerHandler implements ResponseBodyAdvice<Object> {

    public static final Logger logger = LoggerFactory.getLogger(GlobalControllerHandler.class);

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 直接进入封装返回
     *
     * @param returnType
     * @param converterType
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return Objects.equals(VerifyConstant.ENCAPSULATION_RESULT, request.getAttribute(VerifyConstant.ENCAPSULATION_RESULT));
    }

    /**
     * 将返回结果进行封装
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        return body instanceof ResultVO ? body : handleReturnValue(body);
    }

    /**
     * 返回值是否为异常信息
     *
     * @param body
     */
    private ResultVO handleReturnValue(Object body) {
        return body instanceof Exception ? ResultVO.errorOf(((Exception) body).getMessage()) : ResultVO.okOf(body);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomizeException.class)
    public ResultVO handleCustomizeException(CustomizeException e) {
        logger.error("", e);
        return ResultVO.errorOf(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResultVO handleBaseException(ValidationException e) {
        logger.error("", e);
        String desc = applicationContext.getMessage(e.getCode(), e.getArgs(), e.getMsg(), Locale.getDefault());
        if (StringUtils.isEmpty(desc)) {
            logger.info("can not find desc of collector:" + e.getCode());
            desc = e.getCode();
        }
        return ResultVO.errorOf(desc);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedLoginException.class)
    public ResultVO handleFailedLoginException(FailedLoginException e) {
        logger.error("", e);
        return ResultVO.errorOf(ExceptionMessage.AUTH_ACCOUNT_PASSWORD_WRONG);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO handleBaseException(IllegalArgumentException e) {
        logger.error("", e);
        String message = e.getMessage();
        final String nullTarget = "Target must not be null";
        if (StringUtils.isEmpty(message) || Objects.equals(nullTarget, message)) {
            message = "请求参数错误";
        }
        String desc = applicationContext.getMessage(message, null, null, Locale.getDefault());
        if (desc != null) {
            message = desc;
        }
        return ResultVO.errorOf(message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResultVO handleBaseException(Throwable e) {
        logger.error("", e);
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "服务器内部错误";
        }
        return ResultVO.errorOf(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("", e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        if (StringUtils.isEmpty(message)) {
            message = "请求数据格式错误";
        }
        return ResultVO.errorOf(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResultVO handleDataIntegrityViolationException(NullPointerException e) {
        logger.error("", e);
        return ResultVO.errorOf("请求对象不存在");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("", e);
        String eMessage = e.getMessage();
        return ResultVO.errorOf(StringUtils.isEmpty(eMessage) ? "请求参数缺失" : eMessage);
    }

}
