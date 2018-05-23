package com.quick.core.util.type;

public class LongTypeHandler extends   BaseTypeHandler<Long> {
    
    public LongTypeHandler(Long systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Long valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return 0l;
        return Long.valueOf(String.valueOf(object));
    }
}
