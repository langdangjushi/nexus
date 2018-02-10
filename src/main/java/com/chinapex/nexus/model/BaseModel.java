package com.chinapex.nexus.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 数据库Model统一公共字段
 * id:  自增 id
 * createdTime: 建立时间 ， 以系统时间区域为准
 * updatedTime: 更新时间
 * created by pengmingguo on 2/8/18
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"createdTime","updatedTime"})
abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -6387969931129402263L;

    @Id
    @GeneratedValue
    protected Integer id;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    @PreUpdate
    void updateTime() {
        this.updatedTime = new Date();
    }

    @PrePersist
    void createTime() {
        this.createdTime = new Date();
    }
}
