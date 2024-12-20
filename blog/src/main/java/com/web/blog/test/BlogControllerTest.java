package com.web.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogControllerTest {

    @RequestMapping("/blog")
    @ResponseBody
    public String test() {
        return "blog test";
    }  
}