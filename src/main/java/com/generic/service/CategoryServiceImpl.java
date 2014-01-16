package com.generic.service;

import com.generic.dao.CategoryDao;
import com.generic.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: proshad
 * Date: 9/14/13
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> listOfCategory() {
        return categoryDao.listOfCategory();
    }

    @Override
    public List<Category> listOfAllCategoriesAndSubCategory() {
        return categoryDao.listOfAllCategoriesAndSubCategory();
    }

    @Override
    public List<Category> listOfSubCategory(int catID) {
        return categoryDao.listOfSubCategory(catID);
    }

    @Override
    public void saveOrUpdate(Category category) {
        categoryDao.saveOrUpdate(category);
    }

    @Override
    public void removeCategory(int catID) {
        categoryDao.removeCategory(catID);
    }


    @Override
    public Category detailsOfCategory(int catID) {
        return categoryDao.detailsOfCategory(catID);
    }

}
