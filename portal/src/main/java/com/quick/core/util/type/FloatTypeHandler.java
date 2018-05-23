package com.quick.core.util.type;

public class FloatTypeHandler extends   BaseTypeHandler<Float> {
    
    public FloatTypeHandler(Float systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Float valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return 0f;
        return Float.valueOf(String.valueOf(object));
    }
}
