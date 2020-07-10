package com.gestor.jonny.red.SharedActivities.Models;

import java.io.Serializable;

public class ListSelectorModel implements Serializable {
    private String value = "";
    private long id = 0;

    public ListSelectorModel(String value, long id) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
