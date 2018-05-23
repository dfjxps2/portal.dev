package com.quick.core.util.type;

public class DoubleTypeHandler extends   BaseTypeHandler<Double> {
    
    public DoubleTypeHandler(Double systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Double valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return 0d;
        return Double.valueOf(String.valueOf(object));
    }
}
