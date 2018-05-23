package com.quick.core.util.type;



public class CharacterTypeHandler extends   BaseTypeHandler<Character> {
    
    public CharacterTypeHandler(Character systemDefaultValue){
        super(systemDefaultValue);
    }
    
    public CharacterTypeHandler(char systemDefaultValue){
        this(new Character(systemDefaultValue));
    }

    @Override
    public Character valueOf(Object object) {
        return new Character((String.valueOf(object)).charAt(0));
    }
}
