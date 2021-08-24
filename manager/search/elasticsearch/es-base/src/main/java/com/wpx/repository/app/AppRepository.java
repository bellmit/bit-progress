package com.wpx.repository.app;

import com.wpx.model.app.AppESO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author 不会飞的小鹏
 */
public interface AppRepository extends ElasticsearchRepository<AppESO, Long> {
}
