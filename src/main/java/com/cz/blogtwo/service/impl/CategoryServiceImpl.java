package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Category;
import com.cz.blogtwo.mapper.CategoryMapper;
import com.cz.blogtwo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryList() {
        //todo exception
        return categoryMapper.getCategoryList();
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryMapper.getCategoryByName(name);
    }

    @Override
    public void saveCategory(Category category) {
        Integer row=categoryMapper.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        Integer row = categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteCategoryById(String id) {
        Integer row = categoryMapper.deleteCategoryById(id);
    }

    @Override
    public List<Category> getCategoryNameList() {
        List<Category> categoryList = categoryMapper.getCategoryNameList();
        return categoryList;
    }
}
