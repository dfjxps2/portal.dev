package com.quick.core.util.type;

public interface TypeHandler<T> {
  public T parse(Object object, T defaultValue)  throws TypeException;
  public T parse(Object object)  throws TypeException;
}
