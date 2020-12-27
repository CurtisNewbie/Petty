package com.curtisnewbie.dao;

/**
 * @author yongjie.zhuang
 */
public interface MapperFactory {

    Mapper getMapper(MapperType type);

}
