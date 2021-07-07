package com.example.workflow.model;

public enum JavaClass {
	TEST_CLASS("java class","val_class");
	
	private final String key;
    private final String value;
    
    public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
	
    public Info toInfo()
    {
    	return new Info(key,value);
    }
    
	JavaClass(String key, String value) {
		this.key = key;
        this.value = value;
    }
}
