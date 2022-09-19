package com.cz.blogtwo.controller.reveal;

import com.cz.blogtwo.model.vo.BlogInfo;
import com.cz.blogtwo.model.vo.PageResult;
import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.service.CategoryService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分类页面
 */
@RestController
public class CategoryRevealController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    /**
     * 根据分类name分页查询公开博客列表
     *
     * @param categoryName 分类name
     * @param pageNum      页码
     * @return
     */
    @GetMapping("/category")
    public Result category(@RequestParam String categoryName,
                           @RequestParam(defaultValue = "1") Integer pageNum) {
        PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
        return Result.ok("请求成功", pageResult);
    }
}
