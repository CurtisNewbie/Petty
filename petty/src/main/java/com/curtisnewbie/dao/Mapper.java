package com.curtisnewbie.dao;

import com.curtisnewbie.entity.Entity;

import java.util.Optional;

/**
 * @author yongjie.zhuang
 */
public interface Mapper<T extends Entity> {

    Optional<Integer> insert(T entity);

    boolean deleteById(T entity);

    boolean updateById(T entity);
}
