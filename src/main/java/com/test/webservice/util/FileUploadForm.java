package com.test.webservice.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * User: Proshad
 * Date: 1/22/14
 */
public class FileUploadForm {
    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
