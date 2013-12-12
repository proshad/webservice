package com.test.webservice.action;

import com.generic.entity.Category;
import com.generic.entity.Product;
import com.generic.service.CategoryService;
import com.generic.service.ProductService;
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
 * Date: 9/14/13
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public String getAllCategories() {
        List allCategories = new ArrayList();
        List<Category> categories = categoryService.listOfCategory();
        for (Category category : categories) {
            Map categoryMap = new HashMap();
            category = HibernateUtil.unproxy(category);
            categoryMap.put("catId",category.getCategoryID());
            categoryMap.put("name",category.getCategoryName().trim());
            categoryMap.put("description",category.getCategoryDescription().trim());
            categoryMap.put("notes",category.getCategoryNote().trim());
            categoryMap.put("parentId",category.getParentCatID());
            categoryMap.put("status",category.isStatus());


            allCategories.add(categoryMap);
        }

        String json = new Gson().toJson(allCategories);

        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/allProductsOfACategory", method = RequestMethod.GET)
    public String getAllProductsOfACategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List allProducts = new ArrayList();
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        if (categoryID > 0) {
            List<Product> products = productService.listOfProductsOfCategory(categoryID);
            for (Product product : products) {
                Map productMap = new HashMap();
                product = HibernateUtil.unproxy(product);
                productMap.put("productId", product.getProductID());
                productMap.put("name", product.getProductName());
                productMap.put("description", product.getProductDescription());
                productMap.put("notes", product.getProductNote());
                productMap.put("noOfTimeSlot", product.getNoOfTimeSlot());
                productMap.put("status", product.isStatus());

                allProducts.add(productMap);
            }
        }
        String json = new Gson().toJson(allProducts);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllSubCategories/{id}", method = RequestMethod.GET)
    public String getAllSubCategories(@PathVariable("id") int parentCatId) {
        List allCategories = new ArrayList();
        List<Category> categories = categoryService.listOfSubCategory(parentCatId);
        for (Category category : categories) {
            Map categoryMap = new HashMap();
            category = HibernateUtil.unproxy(category);
            categoryMap.put("catId",category.getCategoryID());
            categoryMap.put("name",category.getCategoryName().trim());
            categoryMap.put("description",category.getCategoryDescription().trim());
            categoryMap.put("notes",category.getCategoryNote().trim());
            categoryMap.put("parentId",category.getParentCatID());
            categoryMap.put("status",category.isStatus());

            allCategories.add(categoryMap);
        }
        String json = new Gson().toJson(allCategories);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getCategoryDetails(@PathVariable("id") int catId) {

        Category category = categoryService.detailsOfCategory(catId);
        Map categoryMap = new HashMap();
        category = HibernateUtil.unproxy(category);
        categoryMap.put("catId",category.getCategoryID());
        categoryMap.put("name",category.getCategoryName().trim());
        categoryMap.put("description",category.getCategoryDescription().trim());
        categoryMap.put("notes",category.getCategoryNote().trim());
        categoryMap.put("parentId",category.getParentCatID());
        categoryMap.put("status",category.isStatus());

        String json = new Gson().toJson(categoryMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("category") Category category, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            categoryService.saveOrUpdate(category);
            responseObj.put("status", "success");
            responseObj.put("message", "Category save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer catId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            categoryService.removeCategory(catId);

            responseObj.put("status", "success");
            responseObj.put("message", "Category remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

}
