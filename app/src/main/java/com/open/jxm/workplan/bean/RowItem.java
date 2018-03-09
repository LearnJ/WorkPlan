package com.open.jxm.workplan.bean;

/**
 * Created by jiamao on 2018/3/8.
 */

public class RowItem {

    private String name;
    private String desp;
    private String nextCardID;
    private String lastCardID;

    public String getNextCardID() {
        return nextCardID;
    }

    public void setNextCardID(String nextCardID) {
        this.nextCardID = nextCardID;
    }

    public String getLastCardID() {
        return lastCardID;
    }

    public void setLastCardID(String lastCardID) {
        this.lastCardID = lastCardID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
