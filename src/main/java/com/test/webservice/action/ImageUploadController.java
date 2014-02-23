package com.test.webservice.action;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.generic.entity.Category;
import com.generic.entity.Product;
import com.generic.entity.ProductImage;
import com.generic.service.CategoryService;
import com.generic.service.ProductImageService;
import com.generic.service.ProductService;
import com.test.webservice.util.UploadItem;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Proshad
 * Date: 1/22/14
 */
@Controller
@RequestMapping("/image")
public class ImageUploadController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    private Integer IMAGE_MAX_SIZE = 200000;  //200KB

    // list of allowed file extensions
    private Set<String> allowedImageExtensions;

    // list of messages
    private List<String> messages = new ArrayList<String>();



    public ImageUploadController()
    {
        // define allowed file extensions
        this.allowedImageExtensions = new HashSet<String>();
        this.allowedImageExtensions.add("png");
        this.allowedImageExtensions.add("PNG");
        this.allowedImageExtensions.add("jpg");
        this.allowedImageExtensions.add("JPG");
        this.allowedImageExtensions.add("gif");
        this.allowedImageExtensions.add("GIF");

        // init error messages
        clearMessages();
    }

    // this method is prepares the upload form for display
    @RequestMapping(value = "/showUploadForm", method = RequestMethod.GET)
    public ModelAndView showUploadForm()
    {
        clearMessages();
        ModelAndView mv = new ModelAndView("serviceimage");

        // prepare an item that will store the uploaded file
        mv.addObject("uploadItem", new UploadItem());
        initialize(mv);
        return mv;
    }

    private void initialize(ModelAndView mv){
        JSONArray jArrayCategories = new JSONArray();
        List<Category> categories = categoryService.listOfAllCategoriesAndSubCategory();
        for (Category category : categories) {
            JSONObject obj = new JSONObject();
            obj.put("catID", category.getCategoryID());
            obj.put("name", category.getCategoryName().trim());
            jArrayCategories.add(obj);
        }
        mv.addObject("categories", jArrayCategories);
    }

    // this method will be called when the upload form is submitted
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload (UploadItem uploadItem, HttpSession session) throws Exception
    {
        // the state of the controller is preserved between calls, so each time
        // we need to clear the error messages from the previous submission
        MultipartFile file = uploadItem.getFileData();

        clearMessages();

        try
        {
            if (file.getSize() > 0)
            {
                InputStream inputStream = file.getInputStream();
                String basePath = session.getServletContext().getRealPath("");
                String imageDir = basePath + "/" + "resources/image";
                String fileName = imageDir + "/" + file.getOriginalFilename();
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());

                if (!this.allowedImageExtensions.contains(extension))
                {
                    ModelAndView mv = new ModelAndView("serviceimage");
                    addMessages("Incorrect file extension - only jpg, gif, png are allowed");
                    mv.addObject("messages", this.messages);
                    initialize(mv);
                    return mv;
                }

                if (file.getSize() > IMAGE_MAX_SIZE)
                {
                    ModelAndView mv = new ModelAndView("serviceimage");
                    addMessages("File size too large");
                    mv.addObject("messages", this.messages);
                    initialize(mv);
                    return mv;
                }
                File dir = new File(imageDir);
                File imageFile = new File(imageDir, file.getOriginalFilename());
                OutputStream outputStream = new FileOutputStream(fileName);

                int readBytes = 0;
                byte[] buffer = new byte[IMAGE_MAX_SIZE];
                while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1)
                {
                    outputStream.write(buffer, 0, readBytes);
                }
                outputStream.close();
                inputStream.close();

                ModelAndView mv = new ModelAndView("serviceimage");
                HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String baseUrl = String.format("%s://%s:%d/webservice/resources/image/",request.getScheme(),  request.getServerName(), request.getServerPort());
                String url = baseUrl+""+file.getOriginalFilename();
                addMessages( file.getOriginalFilename() + " has been uploaded successfully");

                ProductImage productImage = new ProductImage();
                try {
                    int productID = Integer.parseInt(uploadItem.getProductID());
                    if(productID > 0){
                        Product product = productService.detailsOfService(productID);
                        productImage.setProduct(product);
                        productImage.setImageUrl(url);
                    }
                    productImageService.saveOrUpdate(productImage);

                } catch (Exception ex) {
                    mv.addObject("messages", ex.getMessage());
                    initialize(mv);
                    return mv;
                }

                mv.addObject("messages", this.messages);



                return mv;
            }
            else
            {
                ModelAndView mv = new ModelAndView("serviceimage");
                addMessages("The file is empty");
                mv.addObject("messages",this.messages);
                initialize(mv);
                return mv;
            }
        }
        catch (Exception e)
        {
            addMessages("Unknown error while uploading the file: " + e.getMessage());
            ModelAndView mv = new ModelAndView("serviceimage");
            mv.addObject("messages", this.messages);
            initialize(mv);
            return mv;
        }
    }



    private void clearMessages()
    {
        this.messages = new ArrayList<String>();
    }

    public void addMessages(String msg)
    {
        this.messages.add(msg);
    }
}
