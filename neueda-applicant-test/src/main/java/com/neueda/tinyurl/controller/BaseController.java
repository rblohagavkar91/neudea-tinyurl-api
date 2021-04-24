package com.neueda.tinyurl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This is base controller which has service to redirect user to Swagger UI page to test the REST API
 */

@Controller
@ApiIgnore
public class BaseController
{

    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
