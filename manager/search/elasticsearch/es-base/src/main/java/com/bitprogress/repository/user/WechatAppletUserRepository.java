package com.bitprogress.repository.user;

import com.bitprogress.model.user.WechatAppletUserESO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 不会飞的小鹏
 */
public interface WechatAppletUserRepository extends ElasticsearchRepository<WechatAppletUserESO, Long> {
}
