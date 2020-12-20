package com.curtisnewbie.entity;

import java.util.Date;

/**
 * Entity for table med_detail
 *
 * @author yongjie.zhuang
 */
public final class MedDetailEntity implements Entity {

    /** Primary key */
    private Integer id;

    /** date */
    private Date date;

    /** remarks */
    private String remarks;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
