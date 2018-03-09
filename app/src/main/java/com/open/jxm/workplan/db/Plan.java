package com.open.jxm.workplan.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by jiamao on 2018/3/8.
 */

public class Plan extends DataSupport implements Serializable{

    private String id;
    private String name;
    private String content;
    private String start;
    private String end;
    private String progress;
    private String next;
    private String extra;
    private String nextCardID;
    private String lastCardID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
