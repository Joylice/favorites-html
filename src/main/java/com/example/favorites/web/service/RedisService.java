package com.example.favorites.web.service;

/**
 * Created by cuiyy on 2017/10/26.
 */
public interface RedisService {
    void set(String key, String value);

    String get(String key);

    void setObject(String key, Object value);

    Object getObject(String key);

    boolean expire(String key, long timeout);

    void delete(String key);
}
