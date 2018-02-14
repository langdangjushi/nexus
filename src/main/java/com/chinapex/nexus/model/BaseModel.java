package com.chinapex.nexus.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据库Model统一公共字段
 * id:  自增 id
 * createdTime: 建立时间 ， 以系统时间区域为准
 * updatedTime: 更新时间
 * 注意子类重写 equals & hashcode 方法时 不要调用父类的方法
 * created by pengmingguo on 2/8/18
 */
@MappedSuperclass
@Getter
@Setter
abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = -6387969931129402263L;

    @Id
    @GeneratedValue
//    @Column(columnDefinition = "unsigned int")
    protected Integer id;

    @Column(name = "gmt_create")
    private Date createdTime;

    @Column(name = "gmt_modified")
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
