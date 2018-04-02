package com.chinapex.nexus.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * created by pengmingguo on 2/14/18
 */
@Entity
@Table(name = "t_user_privilege")
@Getter
@Setter
public class UserPrivilege implements Serializable{

  private static final long serialVersionUID = 1645876716135315208L;

  public static int READ  =  0b1;
  public static int WRITE = 0b10;
  public static int RW    = 0b11;

  @Id
  @ManyToOne
  private User user;

  @Id
  @ManyToOne
  private WebModule webModule;

  private Integer action;

}
