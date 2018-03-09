package com.open.jxm.workplan.bean;

/**
 * Created by jiamao on 2018/3/9.
 */

public class EventInfo {

    private int id;
    private Object obj;

    public EventInfo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
