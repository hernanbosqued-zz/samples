package com.hernan.pruebas;


public class FilterEntity {
    int min;
    int max;
    int stepValue;
    String name;

    public FilterEntity(int min, int max, int stepValue, String name){
        this.min = min;
        this.max = max;
        this.stepValue = stepValue;
        this.name = name;
    }
}
