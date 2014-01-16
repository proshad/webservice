package com.generic.dao;

import com.generic.entity.Category;

import java.util.List;

/**
 * User: proshad
 * Date: 8/19/13
 */
public interface CategoryDao {
    public List<Category> listOfCategory();

    public List<Category> listOfAllCategoriesAndSubCategory();

    public List<Category> listOfSubCategory(int catID);

    public void saveOrUpdate(Category category);

    public void removeCategory(int catID);

    public Category detailsOfCategory(int catID);
}
