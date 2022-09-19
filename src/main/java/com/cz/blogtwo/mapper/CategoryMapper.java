package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 博客分类
 */
@Mapper
@Repository
public interface CategoryMapper {
    List<Category> getCategoryList();

    Category getCategoryById(long id);

    Category getCategoryByName(String name);

    Integer saveCategory(Category category);

    Integer updateCategory(Category category);

    Integer deleteCategoryById(String id);

    List<Category> getCategoryNameList();
}
