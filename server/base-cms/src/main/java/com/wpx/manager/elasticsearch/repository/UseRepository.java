package com.wpx.manager.elasticsearch.repository;

import com.wpx.model.user.user.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

/**
 * @author 不会飞的小鹏
 */
@Service
public interface UseRepository extends ElasticsearchRepository<User, Long> {
}
