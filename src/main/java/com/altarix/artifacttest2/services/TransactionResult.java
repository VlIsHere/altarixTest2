package com.altarix.artifacttest2.services;

import java.util.ArrayList;

public class TransactionResult {
    private ArrayList<Object> result;
    private String message;
    private int code;
    private Object field;

    public TransactionResult(){    }

    public TransactionResult(int code,String message){
        result = new ArrayList<>();
        this.message = message;
        this.code = code;
    }

    public Object getField() {
        return field;
    }

    public void setField(Object field) {
        this.field = field;
    }

    public Object getResult() {
        return result;
    }

    public void addResult(Object result) {
        this.result.add(result);
    }

    public void addResult(ArrayList<Object> list) {
        this.result.addAll(list);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
