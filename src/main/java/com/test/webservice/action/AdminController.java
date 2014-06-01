package com.test.webservice.action;

import com.generic.entity.Category;
import com.generic.entity.Employee;
import com.generic.entity.Rate;
import com.generic.entity.Roster;
import com.generic.service.*;
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

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RosterService rosterService;


    @RequestMapping(value = "/addCategory")
    public String category(ModelMap map) throws Exception {
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        map.put("categories", jArrayCategories);

        return "addcategory";
    }

    @RequestMapping(value = "/addService")
    public String service(ModelMap map) throws Exception {
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfAllCategoriesAndSubCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        map.put("categories", jArrayCategories);

        return "addservice";
    }

    @RequestMapping(value = "/editCategory")
    public String editCategory(ModelMap map) throws Exception {
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfAllCategoriesAndSubCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        map.put("categories", jArrayCategories);

        return "editcategory";
    }

    @RequestMapping(value = "/editService")
    public String editService(ModelMap map) throws Exception {
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfAllCategoriesAndSubCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        map.put("categories", jArrayCategories);

        return "editservice";
    }

    @RequestMapping(value = "/addEmployee")
    public String addEmployee(ModelMap map) throws Exception {
        return "addemployee";
    }

    @RequestMapping(value = "/addRate")
    public String addRate(ModelMap map) throws Exception {
        return "addrate";
    }

    @RequestMapping(value = "/addRoster")
    public String addRoster(ModelMap map) throws Exception {
        return "addroster";
    }
    @RequestMapping(value = "/addServiceRate")
    public String addServiceRate(ModelMap map) throws Exception {
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfAllCategoriesAndSubCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        JSONArray jArrayRates = new JSONArray();
        List<Rate> rates = rateService.listOfRates();
        for (Rate rate : rates) {
            JSONObject obj = new JSONObject();
            obj.put("ID", rate.getRateID());
            obj.put("name", rate.getRateName().trim());
            jArrayRates.add(obj);
        }

        map.put("categories", jArrayCategories);
        map.put("rates", jArrayRates);

        return "addservicerate";
    }

    @RequestMapping(value = "/addEmployeeService")
    public String addEmployeeService(ModelMap map) throws Exception {
        JSONArray jArrayEmployees = new JSONArray();
        List<Employee> employees = employeeService.listOfEmployees();
        for (Employee employee : employees) {
            JSONObject obj = new JSONObject();
            obj.put("ID", employee.getEmployeeID());
            obj.put("name", employee.getFirstName().trim());
            jArrayEmployees.add(obj);
        }

        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfAllCategoriesAndSubCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }

        map.put("categories", jArrayCategories);
        map.put("employees", jArrayEmployees);


        return "addemployeeservice";
    }

    @RequestMapping(value = "/addEmployeeRoster")
    public String addEmployeeRoster(ModelMap map) throws Exception {
        JSONArray jArrayEmployees = new JSONArray();
        List<Employee> employees = employeeService.listOfEmployees();
        for (Employee employee : employees) {
            JSONObject obj = new JSONObject();
            obj.put("ID", employee.getEmployeeID());
            obj.put("name", employee.getFirstName().trim());
            jArrayEmployees.add(obj);
        }

        JSONArray jArrayRosters = new JSONArray();
        List<Roster> rosters = rosterService.listOfRoster();
        for (Roster roster : rosters) {
            JSONObject obj = new JSONObject();
            obj.put("ID", roster.getRosterID());
            obj.put("name", roster.getName().trim());
            jArrayRosters.add(obj);
        }

        map.put("employees", jArrayEmployees);
        map.put("rosters", jArrayRosters);

        return "addemployeeroster";
    }

}
