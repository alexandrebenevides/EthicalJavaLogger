package com.mycompany.ethicaljavalogger.constants;

public enum FileType {
    IMAGE_PNG(" image/png"),
    TEXT("text/plain");
    
    private final String type;
    
    private FileType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}
