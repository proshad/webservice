package com.test.webservice.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Proshad
 * Date: 1/10/14
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public String manageCategory(ModelMap map, HttpServletRequest request) throws Exception {
        return "index";
    }
}
