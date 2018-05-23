package com.quick.core.util.type;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class TypeHandlerFactory {

    @SuppressWarnings("unchecked")
    private Map<Object, TypeHandler<?>> typeHandlerMap;

    private static TypeHandlerFactory factory;

    private TypeHandlerFactory() {
        typeHandlerMap = new HashMap<Object, TypeHandler<?>>();
    }

    public static TypeHandlerFactory newInstance() {
        if (factory == null) {
            factory = new TypeHandlerFactory();
            factory.register(String.class, new StringTypeHandler());
            factory.register(int.class, new IntegerTypeHandler(0));
            factory.register(Integer.class, new IntegerTypeHandler(null));
            factory.register(float.class, new FloatTypeHandler(0f));
            factory.register(Float.class, new FloatTypeHandler(null));
            factory.register(double.class, new DoubleTypeHandler(0d));
            factory.register(Double.class, new DoubleTypeHandler(null));
            factory.register(long.class, new LongTypeHandler(0L));
            factory.register(Long.class, new LongTypeHandler(null));
            factory.register(byte.class, new ByteTypeHandler(new Integer(0).byteValue()));
            factory.register(Byte.class, new ByteTypeHandler(null));
            factory.register(char.class, new CharacterTypeHandler('\u0000'));
            factory.register(Character.class, new CharacterTypeHandler(null));
            factory.register(short.class, new ShortTypeHandler(new Integer(0).shortValue()));
            factory.register(Short.class, new ShortTypeHandler(null));
            factory.register(boolean.class, new BooleanTypeHandler(false));
            factory.register(Boolean.class, new BooleanTypeHandler(false));
            factory.register(Date.class, new DateTypeHandler(null));
        }
        return factory;
    }

    
    @SuppressWarnings("unchecked")
    public TypeHandler<?> getTypeHandler(Class javaType){
        TypeHandler typeHandler = typeHandlerMap.get(javaType);
        return typeHandler== null?new DefaultTypeHandler():typeHandler;
    }

    @SuppressWarnings("unchecked")
    public void register(Class javaType, TypeHandler<?> handler) {
        typeHandlerMap.put(javaType, handler);
    }

}
