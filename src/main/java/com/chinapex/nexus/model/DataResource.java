package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * created by pengmingguo on 2/9/18
 */
@Entity
@Table(name = "t_data_resource")
@Getter
@Setter
public class DataResource extends ManyToOrganization {

    public static int SYNCED = 0;
    public static int SYNCING = 1;

    private static final long serialVersionUID = -5235933824427666763L;

    private String name;

    @OneToOne
    @JoinColumn(name = "data_resource_type_id")
    private DataResourceType dataResourceType;

    @Column(columnDefinition = "TINYINT")
    private Integer status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "config_id")
    private Config config;
}
