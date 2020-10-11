package com.example.dailyexpense.Adapter;

public class Data {
    String fieldName;
    String fieldValue;
    public Data(String fieldName,String fieldValue) {
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }


}
