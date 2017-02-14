package com.art.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Artur Belogur on 04.02.17.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String hello() {
        return "index";
    }
}
