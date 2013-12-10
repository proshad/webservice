package com.test.webservice.action;

import com.generic.entity.*;
import com.generic.service.*;
import com.google.gson.Gson;
import com.test.webservice.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: proshad
 * Date: 11/17/13
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeProductService employeeProductService;

    @Autowired
    private ProductService productService;

    @Autowired
    private EmployeeRosterService employeeRosterService;

    @Autowired
    private RosterService rosterService;



    @ResponseBody
    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public String getAllEmployees(){
        List allEmployees = new ArrayList();
        List<Employee> employees = employeeService.listOfEmployees();
        for (Employee employee : employees) {
            Map employeeMap = new HashMap();
            employee = HibernateUtil.unproxy(employee);
            employeeMap.put("employeeId", employee.getEmployeeID());
            employeeMap.put("firstName", employee.getFirstName().trim());
            employeeMap.put("lastName", employee.getLastName().trim());
            employeeMap.put("designation", employee.getDesignation().trim());
            employeeMap.put("address", employee.getAddress());
            employeeMap.put("contactNo", employee.getContactNo());
            employeeMap.put("imageUrl", employee.getImageUrl());
            if (employee.getJoiningDate() != null) {
                employeeMap.put("joiningDate", employee.getJoiningDate().toString());
            }

            allEmployees.add(employeeMap);
        }

        String json = new Gson().toJson(allEmployees);

        return json;
    }


    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getEmployeeDetails(@PathVariable("id") int employeeId) {

        Employee employee = employeeService.detailsOfEmployee(employeeId);
        List<EmployeeProduct> employeeProducts = employeeProductService.listOfProduct(employeeId);

        Map employeeMap = new HashMap();
        employee = HibernateUtil.unproxy(employee);
        employeeMap.put("employeeId", employee.getEmployeeID());
        employeeMap.put("firstName", employee.getFirstName().trim());
        employeeMap.put("lastName", employee.getLastName().trim());
        employeeMap.put("designation", employee.getDesignation().trim());
        employeeMap.put("address", employee.getAddress());
        employeeMap.put("contactNo", employee.getContactNo());
        employeeMap.put("imageUrl", employee.getImageUrl());
        if (employee.getJoiningDate() != null) {
            employeeMap.put("joiningDate", employee.getJoiningDate().toString());
        }
        List allProducts = new ArrayList();
        for (EmployeeProduct employeeProduct : employeeProducts) {
            Map productMap = new HashMap();
            productMap.put("productId", employeeProduct.getProduct().getProductID());
            productMap.put("productName", employeeProduct.getProduct().getProductName());
            productMap.put("categoryId", employeeProduct.getProduct().getCategory().getCategoryID());
            productMap.put("categoryName", employeeProduct.getProduct().getCategory().getCategoryName());

            allProducts.add(productMap);
        }

        if(allProducts.size()>0){
            employeeMap.put("involveService", allProducts);
        }

        String json = new Gson().toJson(employeeMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/assignService", method = RequestMethod.POST)
    public String employeeToService(@ModelAttribute("employeeProduct") EmployeeProduct employeeProduct, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            Employee employee = employeeService.detailsOfEmployee(employeeID);
            if(employee !=null){
                employeeProduct.setEmployee(employee);
            }
            Product product = productService.detailsOfService(productID);
            if(product!=null){
                employeeProduct.setProduct(product);
            }
            employeeProductService.saveOrUpdate(employeeProduct);
            responseObj.put("status", "success");
            responseObj.put("message", "Employee assigned to service successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }




    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdateEmployee(@ModelAttribute("employee") Employee employee, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            employeeService.saveOrUpdate(employee);
            responseObj.put("status", "success");
            responseObj.put("message", "Employee save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer employeeId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            employeeService.removeEmployee(employeeId);

            responseObj.put("status", "success");
            responseObj.put("message", "Employee remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }


    @ResponseBody
    @RequestMapping(value = "/assignRoster", method = RequestMethod.POST)
    public String employeeToRoster(@ModelAttribute("employeeRoster") EmployeeRoster employeeRoster, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int employeeID = Integer.parseInt(request.getParameter("employeeID"));
            int rosterID = Integer.parseInt(request.getParameter("rosterID"));
            Employee employee = employeeService.detailsOfEmployee(employeeID);
            if(employee !=null){
                employeeRoster.setEmployee(employee);
            }
            Roster roster = rosterService.detailsOfRoster(rosterID);
            if(roster!=null){
                employeeRoster.setRoster(roster);
            }
            employeeRosterService.saveOrUpdate(employeeRoster);
            responseObj.put("status", "success");
            responseObj.put("message", "Employee assigned to a roster successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }


}
