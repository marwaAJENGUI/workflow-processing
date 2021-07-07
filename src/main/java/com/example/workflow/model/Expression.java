package com.example.workflow.model;

public enum Expression {
	TEST_Expression("expression","val_expression");
	
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
    
	Expression(String key, String value) {
		this.key = key;
        this.value = value;
    }
}
