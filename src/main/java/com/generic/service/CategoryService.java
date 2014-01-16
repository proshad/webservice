package com.generic.service;

import com.generic.entity.Category;

import java.util.List;

/**
 * User: proshad
 * Date: 9/14/13
 */
public interface CategoryService {
    public List<Category> listOfCategory();

    public List<Category> listOfAllCategoriesAndSubCategory();

    public List<Category> listOfSubCategory(int catID);

    public void saveOrUpdate(Category category);

    public void removeCategory(int catID);

    public Category detailsOfCategory(int catID);
}
