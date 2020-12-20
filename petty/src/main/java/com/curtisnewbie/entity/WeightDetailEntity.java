package com.curtisnewbie.entity;

import java.util.Date;

/**
 * Entity for table weight_detail
 *
 * @author yongjie.zhuang
 */
public final class WeightDetailEntity {

    /** Primary key */
    private Integer id;

    /** date */
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
