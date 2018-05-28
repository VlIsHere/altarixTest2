package com.altarix.artifacttest2.services;

import java.util.ArrayList;

public class TransactionResult {
    private ArrayList<Object> result;
    private String message;
    private int code;
    private Object field;

    public TransactionResult(){    }

    TransactionResult(int code,String message){
        result = new ArrayList<>();
        this.message = message;
        this.code = code;
    }

    Object getField() {
        return field;
    }

    void setField(Object field) {
        this.field = field;
    }

    Object getResult() {
        return result;
    }

    void addResult(Object result) {
        this.result.add(result);
    }

    void addResult(ArrayList<Object> list) {
        this.result.addAll(list);
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    int getCode() {
        return code;
    }

    void setCode(int code) {
        this.code = code;
    }
}
