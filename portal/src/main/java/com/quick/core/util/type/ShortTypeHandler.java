package com.quick.core.util.type;

public class ShortTypeHandler extends   BaseTypeHandler<Short> {
    
    public ShortTypeHandler(Short systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Short valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return 0;
        return Short.valueOf(String.valueOf(object));
    }
}
