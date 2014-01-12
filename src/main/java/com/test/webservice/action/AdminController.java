package com.test.webservice.action;

import com.generic.entity.Category;
import com.generic.service.CategoryService;
import com.generic.service.ProductRateService;
import com.generic.service.ProductService;
import com.generic.service.RateService;
import com.test.webservice.util.HibernateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Proshad
 * Date: 1/10/14
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRateService productRateService;

    @Autowired
    private RateService rateService;


    @RequestMapping(value = "/category")
    public String category(ModelMap map) throws Exception {
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        map.put("categories",jArrayCategories);

        return "index";
    }
}
