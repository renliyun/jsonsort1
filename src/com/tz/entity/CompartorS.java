package com.tz.entity;

import java.util.Comparator;

public class CompartorS implements Comparator<CompartorS> {
    private String key;

    private Integer value;

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public int compare(CompartorS o1, CompartorS o2) {
        return o1.value - o2.value;
    }

    @Override
    public String toString() {
        return "CompartorS{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}

