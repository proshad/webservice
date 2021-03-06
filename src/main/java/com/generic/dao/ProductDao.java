package com.generic.dao;

import com.generic.entity.Product;
import java.util.List;

/**
 * User: proshad
 * Date: 9/14/13
 */
public interface ProductDao {
    public List<Product> listOfProducts();

    public void saveOrUpdate(Product product);

    public void removeService(int productID);

    public Product detailsOfService(int productID);

    public List<Product> listOfProductsOfCategory(int categoryID);
}
