package com.quick.core.util.type;

public class DefaultTypeHandler implements TypeHandler<Object> {

    public Object parse(Object object, Object defaultValue) throws TypeException {
        throw new TypeException("找不到类型相应的处理类,请重新注册!");
    }

    public Object parse(Object object) throws TypeException {
        return parse(null,null);
    }
   
}
