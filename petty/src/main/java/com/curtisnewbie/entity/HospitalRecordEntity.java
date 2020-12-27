package com.curtisnewbie.entity;

import java.util.Date;

/**
 * Entity for table hospital_record
 *
 * @author yongjie.zhuang
 */
public final class HospitalRecordEntity implements Entity {

    /** Primary key */
    private Integer id;

    /** date */
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "HospitalRecordEntity{" + "id=" + id + ", date=" + createDate + '}';
    }
}

