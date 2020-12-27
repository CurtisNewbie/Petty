package com.curtisnewbie.entity;

import java.util.Date;

/**
 * Entity for poo_detail
 *
 * @author yongjie.zhuang
 */
public final class PooDetailEntity implements Entity {

    /** Primary key */
    private Integer id;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
