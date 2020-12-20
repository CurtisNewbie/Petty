package com.curtisnewbie.dao;

import com.curtisnewbie.entity.Entity;

import java.util.Optional;

/**
 * @author yongjie.zhuang
 */
public interface Mapper<T extends Entity> {

    /**
     * Insert entity and return the generated primary key
     */
    Optional<Integer> insert(T entity);

    /**
     * Delete entity by primary key
     */
    boolean deleteById(T entity);

    /**
     * Update entity by primary key
     */
    boolean updateById(T entity);
}
