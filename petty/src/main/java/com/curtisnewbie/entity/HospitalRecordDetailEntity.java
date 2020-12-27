package com.curtisnewbie.entity;

import java.util.Date;

/**
 * Entity for table hospital_record_detail
 *
 * @author yongjie.zhuang
 */
public final class HospitalRecordDetailEntity implements Entity {

    /** primary key */
    private Integer id;

    /** primary key of hospital_record_detail */
    private Integer hospitalRecordPk;

    /** 1-injection, 2-medicine */
    private Integer type;

    /** date */
    private Date createDate;

    /** remarks */
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHospitalRecordPk() {
        return hospitalRecordPk;
    }

    public void setHospitalRecordPk(Integer hospitalRecordPk) {
        this.hospitalRecordPk = hospitalRecordPk;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
