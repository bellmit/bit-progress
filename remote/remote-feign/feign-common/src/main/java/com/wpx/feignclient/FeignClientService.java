package com.wpx.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

/**
 * 基础的feign调用方法，从URI中解析服务名
 */
@FeignClient(name = "feign")
public interface FeignClientService {

    /**
     * 创建自定义的feignClient -- PUT
     *
     * @param uri
     * @param data
     */
    @RequestMapping(method = RequestMethod.PUT)
    String createPUTFeignClient(URI uri, @RequestBody Object data);

    /**
     * 创建自定义的feignClient -- POST
     *
     * @param uri
     * @param data
     */
    @RequestMapping(method = RequestMethod.POST)
    String createPOSTFeignClient(URI uri, @RequestBody Object data);

    /**
     * 创建自定义的feignClient -- DELETE
     *
     * @param uri
     * @param data
     */
    @RequestMapping(method = RequestMethod.DELETE)
    String createDELETEFeignClient(URI uri, @RequestBody Object data);

    /**
     * 创建自定义的feignClient -- GET
     *
     * @param uri
     * @param data
     */
    @RequestMapping(method = RequestMethod.GET)
    String createGETFeignClient(URI uri, @RequestBody Object data);

}
