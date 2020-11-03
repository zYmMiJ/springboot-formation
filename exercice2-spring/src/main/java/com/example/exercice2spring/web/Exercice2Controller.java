package com.example.exercice2spring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
public class Exercice2Controller {
    private static final Logger logger =
            LoggerFactory.getLogger(Exercice2Controller.class);
    private GlobalProperties globals;
    protected AppProperties apps;

    @Autowired
    public void setApps(AppProperties apps) {
        this.apps = apps;
    }

    @Autowired
    public void setGlobals(GlobalProperties global) {
        this.globals = global;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        String globalProperties = globals.toString();
        String appProperties = apps.toString();

        logger.debug(globalProperties);
        logger.debug("app {}, {}", apps, globals);

        model.put("message", globalProperties + " " + appProperties);
        return "welcome";
    }

}
