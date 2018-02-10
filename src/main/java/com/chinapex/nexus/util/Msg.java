package com.chinapex.nexus.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * created by pengmingguo on 2/9/18
 */
@Getter
@Setter
@AllArgsConstructor
public class Msg implements Serializable{
    private static final long serialVersionUID = -2417860503542275729L;

    public Msg(Integer status) {
        this.status = status;
    }

    public static Integer OK = 200;

    public static Integer ERR = 400;

    private Integer status;

    private String action;

    private Object data;


    public static Msg ok() {
        return new Msg(OK);
    }

    public static Msg err() {
        return new Msg(ERR);
    }

    public Msg data(Object o) {
        this.data = o;
        return this;
    }
}
