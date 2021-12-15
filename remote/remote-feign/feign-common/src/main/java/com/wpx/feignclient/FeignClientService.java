package com.wpx.feignclient;

import com.wpx.model.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * 基础的feign调用方法，从URI中解析服务名
 * @author 不会飞的小鹏
 */
@FeignClient(name = "feign", fallbackFactory = FeignClientServiceFallback.class)
public interface FeignClientService {

    /**
     * 创建自定义的feignClient -- GET
     *
     * @param uri
     * @param data
     * @return ResultVO<String>
     */
    @GetMapping
    ResultVO<String> createGetFeignClient(URI uri, @SpringQueryMap Object data);

    /**
     * 创建自定义的feignClient -- POST
     *
     * @param uri
     * @param data
     * @return ResultVO<String>
     */
    @PostMapping
    ResultVO<String> createPostFeignClient(URI uri, @RequestBody Object data);

    /**
     * 创建自定义的feignClient -- PUT
     *
     * @param uri
     * @param data
     * @return ResultVO<String>
     */
    @PutMapping
    ResultVO<String> createPutFeignClient(URI uri, @RequestBody Object data);

    /**
     * 创建自定义的feignClient -- DELETE
     *
     * @param uri
     * @param data
     * @return ResultVO<String>
     */
    @DeleteMapping
    ResultVO<String> createDeleteFeignClient(URI uri, @SpringQueryMap Object data);

}
