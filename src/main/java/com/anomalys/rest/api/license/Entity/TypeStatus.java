package com.anomalys.rest.api.license.Entity;

public enum TypeStatus {
    AKTIF("aktif"),
    MATI("mati");

    String values ;
    TypeStatus(String values) {
        this.values = values ;
    }
    public String getValues() {
        return values;
    }
}
