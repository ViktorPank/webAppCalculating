package com.pankin.dto;


public class DTOCalculations {
    private String rawData;
    private String data;
    private String typeExample;
    private String resultData;

    public DTOCalculations(String rawData) {
        this.rawData = rawData;
    }

    public DTOCalculations() {}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getTypeExample() {
        return typeExample;
    }

    public void setTypeExample(String typeExample) {
        this.typeExample = typeExample;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}
