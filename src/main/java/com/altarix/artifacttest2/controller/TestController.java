package com.altarix.artifacttest2.controller;

import com.altarix.artifacttest2.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final TestModel model;

    @Autowired
    public TestController(TestModel model) {
        this.model = model;
    }

    @RequestMapping(value = "/sayHello/{num}", method = RequestMethod.GET)
    public String sayHello(@PathVariable(value = "num") Integer num){
        model.setName("Hello, Vlad! You requested num is " + num);
        return model.toString();
    }
}
