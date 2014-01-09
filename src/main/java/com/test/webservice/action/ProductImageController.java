package com.test.webservice.action;

import com.generic.entity.Product;
import com.generic.entity.ProductImage;
import com.generic.service.ProductImageService;
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
 * User: Proshad
 * Date: 12/5/13
 */
@Controller
@RequestMapping("/serviceImage")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/getAllImagesOfAService", method = RequestMethod.GET)
    public String getAllImagesOfAProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List allImages = new ArrayList();
        int productID = Integer.parseInt(request.getParameter("productID"));
        List<ProductImage> productImages = productImageService.getAllImagesOfAProduct(productID);
        for (ProductImage productImage : productImages) {
            Map productImageMap = new HashMap();
            productImage = HibernateUtil.unproxy(productImage);
            productImageMap.put("productID", productImage.getProduct().getProductID());
            productImageMap.put("imageUrl", productImage.getImageUrl().trim());
            allImages.add(productImageMap);
        }
        String json = new Gson().toJson(allImages);
        return json;
    }


    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("productImage") ProductImage productImage, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            if(productID > 0){
                Product product = productService.detailsOfService(productID);
                productImage.setProduct(product);
            }
            productImageService.saveOrUpdate(productImage);
            responseObj.put("status", "success");
            responseObj.put("message", "Product-image save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer productImageID) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            productImageService.removeImageOfAProduct(productImageID);

            responseObj.put("status", "success");
            responseObj.put("message", "Image remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }



}
