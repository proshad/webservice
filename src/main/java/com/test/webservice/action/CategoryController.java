package com.test.webservice.action;

import com.generic.entity.Category;
import com.generic.entity.Product;
import com.generic.entity.ProductRate;
import com.generic.entity.Rate;
import com.generic.service.CategoryService;
import com.generic.service.ProductRateService;
import com.generic.service.ProductService;
import com.generic.service.RateService;
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

    @Autowired
    private ProductRateService productRateService;

    @Autowired
    private RateService rateService;

    @ResponseBody
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public String getAllCategories() {
        Map responseMap = new HashMap();
        List allCategories = new ArrayList();
        List<Category> categories = categoryService.listOfCategory();
        for (Category category : categories) {
            Map categoryMap = new HashMap();
            category = HibernateUtil.unproxy(category);
            categoryMap.put("catID", category.getCategoryID());
            categoryMap.put("name", category.getCategoryName().trim());
//            categoryMap.put("description",category.getCategoryDescription().trim());
//            categoryMap.put("notes",category.getCategoryNote().trim());
//            categoryMap.put("parentId",category.getParentCatID());
            categoryMap.put("type", 1); // type 1 means parent category


            allCategories.add(categoryMap);
        }
        responseMap.put("result", allCategories);
        responseMap.put("status", "success");

        String json = new Gson().toJson(responseMap);

        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllSubCategoriesAndServices", method = RequestMethod.GET)
    public String allServicesOfACategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map responseMap = new HashMap();
        Map serviceCategoryMap = new HashMap();
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        if (categoryID > 0) {
            List allServices = new ArrayList();
            List<Product> products = productService.listOfProductsOfCategory(categoryID);
            for (Product product : products) {
                Map productMap = new HashMap();
                product = HibernateUtil.unproxy(product);
                productMap.put("serviceID", product.getProductID());
                productMap.put("name", product.getProductName());
//                productMap.put("description", product.getProductDescription());
                productMap.put("notes", product.getProductNote());
//                productMap.put("noOfTimeSlot", product.getNoOfTimeSlot());
//                productMap.put("type", 3);  // type =3, means service

                // get default price of this product
                List<ProductRate> productRates = productRateService.getAllRatesOfAProduct(product.getProductID());
                for (ProductRate pRate : productRates) {
                    Rate rate = rateService.detailsOfRate(pRate.getRate().getRateID());
                    String name = rate.getRateName().trim();
                    if(name.equalsIgnoreCase("default")){
                        productMap.put("price", rate.getPrice());
                        break;
                    }
                }

                allServices.add(productMap);
            }

            // for sub-category
            List allCategories = new ArrayList();
            List<Category> categories = categoryService.listOfSubCategory(categoryID);
            for (Category category : categories) {
                Map categoryMap = new HashMap();
                category = HibernateUtil.unproxy(category);
                categoryMap.put("catID", category.getCategoryID());
                categoryMap.put("name", category.getCategoryName().trim());
//                categoryMap.put("description", category.getCategoryDescription().trim());
                categoryMap.put("notes", category.getCategoryNote().trim());
//                categoryMap.put("parentId", categoryID);
//                categoryMap.put("status", category.isStatus());

                allCategories.add(categoryMap);
            }
            if (allCategories.size() > 0) {
                serviceCategoryMap.put("subCategory", allCategories);
            }
            if (allServices.size() > 0) {
                serviceCategoryMap.put("services", allServices);
            }
            responseMap.put("result", serviceCategoryMap);
            responseMap.put("status", "success");
        }
        String json = new Gson().toJson(responseMap);
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
            categoryMap.put("catID", category.getCategoryID());
            categoryMap.put("name", category.getCategoryName().trim());
            categoryMap.put("description", category.getCategoryDescription().trim());
            categoryMap.put("notes", category.getCategoryNote().trim());
            categoryMap.put("parentID", category.getParentCatID());
            categoryMap.put("status", category.isStatus());

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
        categoryMap.put("catID", category.getCategoryID());
        categoryMap.put("name", category.getCategoryName().trim());
        categoryMap.put("description", category.getCategoryDescription().trim());
        categoryMap.put("notes", category.getCategoryNote().trim());
        categoryMap.put("parentID", category.getParentCatID());
        categoryMap.put("status", category.isStatus());

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
