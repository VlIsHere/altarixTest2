package com.altarix.artifacttest2.model;

import org.springframework.stereotype.Component;

@Component
public class TestModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
