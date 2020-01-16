package com.hacknife.atlas.bean;

import com.hacknife.onlite.annotation.Column;
import com.hacknife.onlite.annotation.Table;
import com.hacknife.onlite.annotation.Unique;

@Table
public class MapStringInteger {
    @Unique
    @Column("map_key")
    String key;
    @Column("map_value")
    Integer value;

    public MapStringInteger() {
    }

    public MapStringInteger(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "\"key\":\'" + key + "\'" +
                ", \"value\":\'" + value + "\'" +
                '}';
    }
}
