package com.example.dailyexpense.Adapter;

public class ModelData {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String name;
    int value;
    String date;
    public ModelData(String name,int value ,String date) {
        this.name = name;
      this.date=date;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
