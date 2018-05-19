package com.altarix.artifacttest2.controllers;

import com.altarix.artifacttest2.models.ModelLayer;
import com.altarix.artifacttest2.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final ModelLayer model;
    private final View view;

    @Autowired
    public TestController(ModelLayer model, View view) {
        this.model = model;
        this.view = view;
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(@PathVariable(value = "num") Integer num){
        return "nop";
    }
}
