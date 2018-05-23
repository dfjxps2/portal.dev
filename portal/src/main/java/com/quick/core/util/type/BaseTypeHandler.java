package com.quick.core.util.type;

import java.lang.reflect.ParameterizedType;

public abstract class BaseTypeHandler<T> implements TypeHandler<T> {
    private T systemDefaultValue;

    private Class<T> clazz;

    public BaseTypeHandler() {
        systemDefaultValue = null;
    }

    public BaseTypeHandler(T systemDefaultValue) {
        this.systemDefaultValue = systemDefaultValue;
    }

    @SuppressWarnings("unchecked")
    private Class<T> getType() throws TypeException {
        if (clazz == null) {
            try {
                ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
                this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
            } catch (Exception e) {
                throw new TypeException(e.getMessage());
            }
        }
        return clazz;
    }

    public void setSystemDefaultValue(T systemDefaultValue) {
        this.systemDefaultValue = systemDefaultValue;
    }

    public T getSystemDefaultValue() {
        return systemDefaultValue;
    }

    protected boolean isnull(Object object) {
        if (object == null) {
            return true;
        } else if ("null".equals(object.toString())) {
            return true;
        } else if ("".equals(String.valueOf(object).trim())) {
            return true;
        } else if ("{}".equals(object.toString())) {
            return true;
        } else {
            return false;
        }
    }

    public T parse(Object object) throws TypeException {
        return parse(object, getSystemDefaultValue());
    }

    @SuppressWarnings("unchecked")
    public T parse(Object object, T defaultValue) throws TypeException {
        if (isnull(object)) {
            return defaultValue;
        }

        if (getType().isInstance(object)) {
            return (T) object;
        }

        try {
            return valueOf(object);
        } catch (Exception e) {
            throw new TypeException(e.getMessage());
        }
    }


    public abstract T valueOf(Object object) throws TypeException;
}
