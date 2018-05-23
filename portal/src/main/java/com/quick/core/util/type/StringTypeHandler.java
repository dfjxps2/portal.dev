package com.quick.core.util.type;

public class StringTypeHandler extends   BaseTypeHandler<String> {

    @Override
    public String getSystemDefaultValue() {
        return "";
    }

    @Override
    public String valueOf(Object object) {
        return String.valueOf(object);
    }


  
}
