package com.wpx.feignclient;

import com.wpx.model.ResultVO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class FeignClientServiceFallback implements FallbackFactory<FeignClientService> {
    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public FeignClientService create(Throwable cause) {
        return new FeignClientService() {
            /**
             * 创建自定义的feignClient -- GET
             *
             * @param uri
             * @param data
             * @return ResultVO<String>
             */
            @Override
            public ResultVO<String> createGetFeignClient(URI uri, Object data) {
                return null;
            }

            /**
             * 创建自定义的feignClient -- POST
             *
             * @param uri
             * @param data
             * @return ResultVO<String>
             */
            @Override
            public ResultVO<String> createPostFeignClient(URI uri, Object data) {
                return null;
            }

            /**
             * 创建自定义的feignClient -- PUT
             *
             * @param uri
             * @param data
             * @return ResultVO<String>
             */
            @Override
            public ResultVO<String> createPutFeignClient(URI uri, Object data) {
                return null;
            }

            /**
             * 创建自定义的feignClient -- DELETE
             *
             * @param uri
             * @param data
             * @return ResultVO<String>
             */
            @Override
            public ResultVO<String> createDeleteFeignClient(URI uri, Object data) {
                return null;
            }
        };
    }
}
