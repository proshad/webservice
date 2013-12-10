package com.generic.service;

import com.generic.entity.ProductImage;

import java.util.List;

/**
 * User: Proshad
 * Date: 12/5/13
 */
public interface ProductImageService {
    public List<ProductImage> getAllImagesOfAProduct(int productID);

    public void saveOrUpdate(ProductImage productImage);

    public void removeImageOfAProduct(int productImageID);
}
