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
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HospitalRecordEntity{" + "id=" + id + ", date=" + date + '}';
    }
}

