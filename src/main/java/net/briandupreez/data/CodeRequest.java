package net.briandupreez.data;

/**
 * Created by brian on 2016/09/11.
 */
public class CodeRequest {
    private String descr;
    private Integer type;
    private int id;

    public String getDescr() {
        return descr;
    }

    public void setDescr(final String descr) {
        this.descr = descr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }
}
