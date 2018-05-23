package com.quick.core.util.type;

public class IntegerTypeHandler extends   BaseTypeHandler<Integer> {
    
    public IntegerTypeHandler(Integer systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Integer valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return 0;
        return Integer.valueOf(String.valueOf(object));
    }
}
