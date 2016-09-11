package net.briandupreez.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by brian on 2016/07/26.
 */
@Entity
public class Code {

    @Id
    private int code_id;
    private String code_descr;
    private Integer type_id;

    public Code() {
    }

    public Code(final int code_id, final String code_descr, final Integer type_id) {
        this.code_id = code_id;
        this.code_descr = code_descr;
        this.type_id = type_id;
    }

    public int getCode_id() {
        return code_id;
    }

    public void setCode_id(final int code_id) {
        this.code_id = code_id;
    }

    public String getCode_descr() {
        return code_descr;
    }

    public void setCode_descr(final String code_descr) {
        this.code_descr = code_descr;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(final Integer type_id) {
        this.type_id = type_id;
    }
}
