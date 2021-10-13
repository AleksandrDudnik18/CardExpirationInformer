package com.sbertask.cardexpirationinformer.service.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import javax.persistence.Table;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class JsonServiceImpl<T, I> {
    private Class<T> type;

    protected void setType(Class<T> type) {
        this.type = type;
    }

    protected Field getIdField(T val) {
        for (Field field : val.getClass().getDeclaredFields())
            if (field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                return field;
            }
        return null;
    }

    protected List<T> readFile() {
        try {
            String json = FileUtils.readFileToString(
                    new File(type.getAnnotation(Table.class).name()),
                    StandardCharsets.UTF_8);
            Type type = new TypeToken<ArrayList<T>>() {
            }.getType();
            List<T> list = new Gson().fromJson(json, type);
            if (list == null) list = new ArrayList<>();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void writeFile(List<T> list) {
        try {
            FileUtils.write(
                    new File(type.getAnnotation(Table.class).name()),
                    new Gson().toJson(list),
                    StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> findAll() {
        return readFile();
    }

    public T save(T val) {
        try {
            List<T> list = findAll();
            list.add(val);
            Field id = getIdField(val);
            id.set(val, list.size() + 1);
            writeFile(list);
            return val;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> saveAll(T... values) {
        if (values.length == 0) return null;
        try {
            List<T> list = findAll();
            Field id = getIdField(values[0]);
            for (T val : values) {
                id.set(val, list.size() + 1);
                list.add(val);
            }
            writeFile(list);
            return Arrays.asList(values);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getById(I id) {
        try {
            List<T> list = findAll();
            if (list.size() == 0)
                return null;
            Field idField = getIdField(list.get(0));
            for (T val : findAll())
                if (idField.get(val).equals(id))
                    return val;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
