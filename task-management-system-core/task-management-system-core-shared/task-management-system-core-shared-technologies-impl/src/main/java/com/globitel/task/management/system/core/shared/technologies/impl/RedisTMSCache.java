
package com.globitel.task.management.system.core.shared.technologies.impl;

import com.globitel.task.management.system.core.shared.technologies.TMSCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;



@RequiredArgsConstructor
public class RedisTMSCache implements TMSCache {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean containsKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
