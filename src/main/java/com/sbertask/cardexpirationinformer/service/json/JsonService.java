package com.sbertask.cardexpirationinformer.service.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sbertask.cardexpirationinformer.models.Client;
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

public abstract class JsonService<T, I> {
    private Class<T> type;

    private Type listType;

    protected void setType(Class<T> type, Type listType) {
        this.type = type;
        this.listType = listType;
    }

    protected Field getIdField(T val) {
        for (Field field : val.getClass().getDeclaredFields())
            if (field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                return field;
            }
        return null;
    }

    protected List<T> readFileJson() {
        try {
            File file = new File(type.getAnnotation(Table.class).name());
            if (file.exists()) {
                String json = FileUtils.readFileToString(
                        file,
                        StandardCharsets.UTF_8);

                List<T> list = new Gson().fromJson(json, listType);
                if (list == null) list = new ArrayList<>();
                return list;
            }
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void writeFileJson(List<T> list) {
        try {
            FileUtils.write(
                    new File(type.getAnnotation(Table.class).name()),
                    new Gson().toJson(list),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected T saveJson(T val) {
        try {
            Field fieldId = getIdField(val);
            Object valId = fieldId.get(val);

            List<T> list = readFileJson();

            Object currentId;
            for (int i = 0; i < list.size(); i++) {
                currentId = fieldId.get(list.get(i));
                if (valId.equals(currentId)) {
                    list.set(i, val);
                    writeFileJson(list);
                    return val;
                }
            }

            list.add(val);
            fieldId.set(val, (long)(list.size()));
            writeFileJson(list);
            return val;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> saveAllJson(List<T> values) {

        try {
            List<T> list = readFileJson();
            for (T val : values) {
                saveJson(val);
            }
            writeFileJson(list);
            return values;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getByIdJson(I id) {
        try {
            List<T> list = readFileJson();
            if (list.size() == 0)
                return null;

            Field idField;
            for (T val : readFileJson()) {
                idField = getIdField(val);
                if (idField.get(val).equals(id))
                    return val;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
