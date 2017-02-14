package com.art.controllers;

import com.art.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Artur Belogur on 04.02.17.
 */
@RestController
public class ApiController {

    @Autowired
    private Publisher publisher;

    @RequestMapping(value = "/del")
    public void del() {
        publisher.getChannel().publish(0);
    }
}
