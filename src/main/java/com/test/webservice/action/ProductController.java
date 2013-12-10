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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    public String getAllCategory() {
        List allProducts = new ArrayList();
        List<Product> products = productService.listOfProducts();
        for (Product product : products) {
            Map productMap = new HashMap();
            product = HibernateUtil.unproxy(product);
            productMap.put("productId", product.getProductID());
            productMap.put("name", product.getProductName());
            productMap.put("description", product.getProductDescription());
            productMap.put("notes", product.getProductNote());
            productMap.put("noOfTimeSlot", product.getNoOfTimeSlot());
            productMap.put("status", product.isStatus());
            productMap.put("categoryId", product.getCategory().getCategoryID());


            allProducts.add(productMap);
        }
        String json = new Gson().toJson(allProducts);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getProductDetails(@PathVariable("id") int productId) {

        Product product = productService.detailsOfService(productId);
        Map productMap = new HashMap();
        product = HibernateUtil.unproxy(product);
        productMap.put("productId", product.getProductID());
        productMap.put("name", product.getProductName());
        productMap.put("description", product.getProductDescription());
        productMap.put("notes", product.getProductNote());
        productMap.put("noOfTimeSlot", product.getNoOfTimeSlot());
        productMap.put("status", product.isStatus());
        productMap.put("categoryId", product.getCategory().getCategoryID());

        String json = new Gson().toJson(productMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("product") Product product, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));

            Category category = categoryService.detailsOfCategory(categoryID);
            if(category != null){
                product.setCategory(category);
            }

            productService.saveOrUpdate(product);
            responseObj.put("status", "success");
            responseObj.put("message", "Product save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }

        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer productId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            productService.removeService(productId);

            responseObj.put("status", "success");
            responseObj.put("message", "Product remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }
}
