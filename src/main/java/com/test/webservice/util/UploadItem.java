package com.test.webservice.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: Proshad
 * Date: 1/22/14
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadItem {
    private String filename;
    private CommonsMultipartFile fileData;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    private String productID;

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public CommonsMultipartFile getFileData()
    {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData)
    {
        this.fileData = fileData;
    }
}
