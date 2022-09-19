package com.cz.blogtwo.controller.reveal;

import com.cz.blogtwo.entity.Blog;
import com.cz.blogtwo.model.vo.BlogDetail;
import com.cz.blogtwo.model.vo.SearchBlog;
import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.utils.Result;
import com.cz.blogtwo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class BlogRevealController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public Result getBlogs (@RequestParam(defaultValue = "1") Integer pageNum){
        return Result.ok("查找成功", blogService.getBlogInfoListByIsPublished(pageNum));
    }

    @GetMapping("/blog")
    public Result getBlogById(@RequestParam String id){
        BlogDetail blog = blogService.getBlogByIdAndIsPublished(id);
        return Result.ok("获取成功", blog);
    }


    /**
     * 按关键字根据文章内容搜索公开且无密码保护的博客文章
     *
     * @param query 关键字字符串
     * @return
     */
    @GetMapping("/searchBlog")
    public Result searchBlog(@RequestParam String query) {//文章正文
        //校验关键字字符串合法性
        if (StringUtils.isEmpty(query) || StringUtils.hasSpecialChar(query) || query.trim().length() > 20) {
            return Result.error("参数错误");
        }
        List<SearchBlog> searchBlogs = blogService.getSearchBlogListByQueryAndIsPublished(query.trim());
        return Result.ok("获取成功", searchBlogs);
    }

}
