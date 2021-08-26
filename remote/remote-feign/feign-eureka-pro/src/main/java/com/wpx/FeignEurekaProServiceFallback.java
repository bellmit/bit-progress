package com.wpx;

import com.wpx.model.ResultVO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 */
@Component
public class FeignEurekaProServiceFallback implements FallbackFactory<FeignEurekaProService> {

    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public FeignEurekaProService create(Throwable cause) {
        return null;
    }
}
