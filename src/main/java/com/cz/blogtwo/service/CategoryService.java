package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryList();

    Category getCategoryById(long id);

    Category getCategoryByName(String name);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(String id);

    List<Category> getCategoryNameList();
}
