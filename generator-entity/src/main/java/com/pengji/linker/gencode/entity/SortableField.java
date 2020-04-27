package com.pengji.linker.gencode.entity;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;

/**
 * Created by lasia on 2020/4/22.
 */
public class SortableField {

    public SortableField() {
    }

    public SortableField(NotNull meta, Field field) {
        super();
        this.meta = meta;
        this.field = field;
        this.name = field.getName();
        this.type = field.getType();
    }


    public SortableField(NotNull meta, String name, Class<?> type) {
        super();
        this.meta = meta;
        this.name = name;
        this.type = type;
    }


    private NotNull meta;
    private Field field;
    private String name;
    private Class<?> type;

    public NotNull getMeta() {
        return meta;
    }

    public void setMeta(NotNull meta) {
        this.meta = meta;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }


}