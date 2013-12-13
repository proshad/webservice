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
 * Date: 9/14/13
 */
@Controller
@RequestMapping("/service")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ProductRateService productRateService;

    @Autowired
    private RateService rateService;

    @Autowired
    private ProductImageService productImageService;


    @ResponseBody
    @RequestMapping(value = "/getAllServices", method = RequestMethod.GET)
    public String getAllServices() {
        List allProducts = new ArrayList();
        List<Product> products = productService.listOfProducts();
        for (Product product : products) {
            Map productMap = new HashMap();
            product = HibernateUtil.unproxy(product);
            productMap.put("productId", product.getProductID());
            productMap.put("name", product.getProductName());
//            productMap.put("description", product.getProductDescription());
//            productMap.put("notes", product.getProductNote());
            productMap.put("noOfTimeSlot", product.getNoOfTimeSlot());
//            productMap.put("status", product.isStatus());
            productMap.put("categoryId", product.getCategory().getCategoryID());


            allProducts.add(productMap);
        }
        String json = new Gson().toJson(allProducts);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetailOfAService", method = RequestMethod.GET)
    public String getDetailOfAService(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        Product product = productService.detailsOfService(serviceID);
        Map productMap = new HashMap();
        Map responseMap = new HashMap();
        product = HibernateUtil.unproxy(product);
        int productID = product.getProductID();
        productMap.put("serviceID", productID);
        productMap.put("categoryID", product.getCategory().getCategoryID());
        productMap.put("name", product.getProductName());
        productMap.put("description", product.getProductDescription());
        productMap.put("notes", product.getProductNote());

        List<Organization> organizations = organizationService.listOfOrganization();
        for (Organization organization : organizations) {
            int slotDuration = organization.getTimeSlotDuration();
            int noOfSlot = product.getNoOfTimeSlot();
            productMap.put("duration", slotDuration * noOfSlot);
        }

        // get default price of this product
        List<ProductRate> productRates = productRateService.getAllRatesOfAProduct(productID);
        List allRates = new ArrayList();
        for (ProductRate pRate : productRates) {
            Rate rate = rateService.detailsOfRate(pRate.getRate().getRateID());
            String name = rate.getRateName().trim();
            if (name.equalsIgnoreCase("default")) {
                productMap.put("price", rate.getPrice());
                continue;
            }
            Map rateMap = new HashMap();
            rate = HibernateUtil.unproxy(rate);
            rateMap.put("rateId", rate.getRateID());
            rateMap.put("name", name);
//            rateMap.put("description", rate.getRateDescription().trim());
            rateMap.put("price", rate.getPrice());
            allRates.add(rateMap);
        }
        if (allRates.size() > 0) {
            productMap.put("specialPrice", allRates);
        }

        // get all images of a service
        List allImages = new ArrayList();
        List<ProductImage> productImages = productImageService.getAllImagesOfAProduct(productID);
        for (ProductImage productImage : productImages) {
            Map productImageMap = new HashMap();
            productImage = HibernateUtil.unproxy(productImage);
            productImageMap.put("imageID", productImage.getProductImageID());
            productImageMap.put("imageUrl", productImage.getImageUrl().trim());
            allImages.add(productImageMap);
        }
        if (allImages.size() > 0) {
            productMap.put("serviceImage", allImages);
        }

        responseMap.put("result", productMap);
        responseMap.put("status", "success");

        String json = new Gson().toJson(responseMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("product") Product product, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));

            if (categoryID > 0) {
                Category category = categoryService.detailsOfCategory(categoryID);
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
