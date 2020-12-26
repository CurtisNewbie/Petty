package com.curtisnewbie.dao;

import com.curtisnewbie.entity.HospitalRecordEntity;

import java.util.List;

/**
 * @author yongjie.zhuang
 */
public interface HospitalRecordMapper extends Mapper<HospitalRecordEntity> {

    List<HospitalRecordEntity> findAll();
}
