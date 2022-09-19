package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.Category;
import com.cz.blogtwo.entity.Moment;
import com.cz.blogtwo.service.CategoryService;
import com.cz.blogtwo.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public Result categories(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//分页查询的条件
        List<Category> categories=categoryService.getCategoryList();
        PageInfo<Category> pageInfo = new PageInfo<>(categories);
        return Result.ok("请求成功", pageInfo);
    }

    @PostMapping("/category")
    public Result InsertCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
        return Result.ok("请求成功");
    }

    @PutMapping("/category")
    public Result updateCategory(@RequestBody Category category){
        categoryService.updateCategory(category);
        return Result.ok("请求成功");
    }

    @DeleteMapping("/category")
    public Result deleteCategory(@RequestParam String id){
        categoryService.deleteCategoryById(id);
        return Result.ok("请求成功");
    }
}
