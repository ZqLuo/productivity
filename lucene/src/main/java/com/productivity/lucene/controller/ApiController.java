package com.productivity.lucene.controller;

import com.productivity.lucene.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private ApplicationConfig applicationConfig;

    @ResponseBody
    @RequestMapping(value = "/search")
    public String search(HttpServletRequest request){
        logger.info("search");
        return "123";
    }

}
