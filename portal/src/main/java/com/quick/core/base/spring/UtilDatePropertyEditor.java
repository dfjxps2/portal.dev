package com.quick.core.base.spring;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException; 
import java.util.Date;

import org.apache.ibatis.io.ResolverUtil.IsA;

public class UtilDatePropertyEditor extends PropertyEditorSupport
{
	private DateFormat dateFormat;  
	private boolean canNull;
	  
    
    

	

	public UtilDatePropertyEditor() {  
    }  
  
    public UtilDatePropertyEditor(DateFormat dateFormat,boolean canNull) {  
        this.dateFormat = dateFormat;  
        this.canNull=canNull;
    }  
  @Override
    public void setAsText(String text) throws IllegalArgumentException { 
    	
        try {  
            Date date = dateFormat.parse(text);  
            this.setValue(date);  
        } catch (Exception e) {  
			try {
				dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				Date date = dateFormat.parse(text);
				 this.setValue(date); 
			} catch (Exception e1) {
				if(canNull)
				  this.setValue(null); 
			}  
          
        }  
    }  
  
    public void setFormat(DateFormat dateFormat) {  
        this.dateFormat = dateFormat;  
    }  
    public void setCanNull(boolean canNull) {
		this.canNull = canNull;
	}
}