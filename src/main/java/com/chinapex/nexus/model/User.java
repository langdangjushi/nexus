package com.chinapex.nexus.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Set;

/**
 * created by pengmingguo on 2/8/18
 */
@Entity
@Table(name = "t_user",
        indexes = {@Index(name = "name_index", columnList = "name", unique = true),
                @Index(name = "email_index", columnList = "email", unique = true)})
@Getter
@Setter
public class User extends ManyToOrganization {

    // 用户角色
    public static String ADMIN = "admin";
    public static String MEMBER = "member";

    // 用户 状态码
    public static int INVITED = 1;
    public static int ACTIVATED = 2;

    @Length(min = 1, max = 100)
    private String name;

    @Email
    private String email;

    @Length(min = 8, max = 50)
    private String password;

    @Column(name = "invite_id")
    private String inviteId;

    @ManyToOne
    @JoinColumn(name = "parent")
    private User parent;

    @OneToMany(mappedBy = "parent")
    private Collection<User> children;

    private String role;

    @Column(columnDefinition = "TINYINT")
    private Integer status;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Collection<UserPrivilege> privileges;
}
