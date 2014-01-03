package com.test.webservice.action;

import com.generic.entity.Product;
import com.generic.entity.ProductRate;
import com.generic.entity.Rate;
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
 * User: Proshad
 * Date: 12/5/13
 */
@Controller
@RequestMapping("/rate")
public class RateController {
    @Autowired
    private RateService rateService;
    @Autowired
    private ProductRateService productRateService;

    @Autowired
    private ProductService productService;


    @ResponseBody
    @RequestMapping(value = "/getAllRates", method = RequestMethod.GET)
    public String getAllRates() {
        List allRates = new ArrayList();
        List<Rate> rates = rateService.listOfRates();
        for (Rate rate : rates) {
            Map rateMap = new HashMap();
            rate = HibernateUtil.unproxy(rate);
            rateMap.put("rateID", rate.getRateID());
            rateMap.put("name", rate.getRateName().trim());
            rateMap.put("description", rate.getRateDescription().trim());
            rateMap.put("price", rate.getPrice());
            rateMap.put("status", rate.isStatus());
            allRates.add(rateMap);
        }
        String json = new Gson().toJson(allRates);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getRateDetails(@PathVariable("id") int rateId) {

        Rate rate = rateService.detailsOfRate(rateId);
        Map rateMap = new HashMap();
        rate = HibernateUtil.unproxy(rate);
        rateMap.put("rateID", rate.getRateID());
        rateMap.put("name", rate.getRateName().trim());
        rateMap.put("description", rate.getRateDescription().trim());
        rateMap.put("price", rate.getPrice());
        rateMap.put("status", rate.isStatus());

        String json = new Gson().toJson(rateMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("rate") Rate rate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            rateService.saveOrUpdate(rate);
            responseObj.put("status", "success");
            responseObj.put("message", "Rate save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer rateId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            rateService.removeRate(rateId);

            responseObj.put("status", "success");
            responseObj.put("message", "Rate remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/productRate", method = RequestMethod.POST)
    public String rateToProduct(@ModelAttribute("productRate") ProductRate productRate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int rateID = Integer.parseInt(request.getParameter("rateID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            Rate rate = rateService.detailsOfRate(rateID);
            if (rate != null) {
                productRate.setRate(rate);
            }
            Product product = productService.detailsOfService(productID);
            if (product != null) {
                productRate.setProduct(product);
            }
            productRateService.saveOrUpdate(productRate);
            responseObj.put("status", "success");
            responseObj.put("message", "Product price has been saved successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/allRatesOfAProduct", method = RequestMethod.GET)
    public String getAllRatesOfAProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = "";
        int productID = Integer.parseInt(request.getParameter("productID"));
        if (productID > 0) {
            List<ProductRate> productRates = productRateService.getAllRatesOfAProduct(productID);
            List allRates = new ArrayList();
            for (ProductRate pRate : productRates) {
                Rate rate = rateService.detailsOfRate(pRate.getRate().getRateID());
                Map rateMap = new HashMap();
                rate = HibernateUtil.unproxy(rate);
                rateMap.put("rateID", rate.getRateID());
                rateMap.put("name", rate.getRateName().trim());
                rateMap.put("description", rate.getRateDescription().trim());
                rateMap.put("price", rate.getPrice());
                rateMap.put("status", rate.isStatus());
                allRates.add(rateMap);
            }
            json = new Gson().toJson(allRates);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/removeRateProduct")
    public String removeRateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            if (productID > 0) {
                productRateService.removeRateOfAProduct(productID);
                responseObj.put("status", "success");
                responseObj.put("message", "Product rate has been removed successfully");
            }

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }
}
