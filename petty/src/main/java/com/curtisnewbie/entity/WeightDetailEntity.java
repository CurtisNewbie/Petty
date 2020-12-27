package com.curtisnewbie.entity;

import java.util.Date;

/**
 * Entity for table weight_detail
 *
 * @author yongjie.zhuang
 */
public final class WeightDetailEntity implements Entity {

    /** Primary key */
    private Integer id;

    /** date */
    private Date createDate;

    /** weight */
    private Double weight;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
