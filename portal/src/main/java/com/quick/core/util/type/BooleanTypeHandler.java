package com.quick.core.util.type;



public class BooleanTypeHandler extends   BaseTypeHandler<Boolean> {
    
    public BooleanTypeHandler(Boolean systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Boolean valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return false;
        return Boolean.valueOf(String.valueOf(object));
    }
}
