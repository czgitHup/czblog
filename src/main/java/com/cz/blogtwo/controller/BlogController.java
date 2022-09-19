package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.Blog;
import com.cz.blogtwo.entity.Category;
import com.cz.blogtwo.entity.Tag;
import com.cz.blogtwo.entity.User;
import com.cz.blogtwo.model.dto.BlogVisibility;
import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.service.CategoryService;
import com.cz.blogtwo.service.TagService;
import com.cz.blogtwo.utils.Result;
import com.cz.blogtwo.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @GetMapping("/blogs")
    public Result blogs(@RequestParam(defaultValue = "") String title,
                        //@RequestParam(defaultValue = "")设置默认值必须为string类型
                        @RequestParam(defaultValue = "") Integer categoryId,
                        @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "create_time desc";//创建时间降序
        PageHelper.startPage(pageNum, pageSize, orderBy);//分页查询的条件
        List<Blog> blogs = blogService.getListByTitleAndCategoryId(title, categoryId);//查询，非空判断在xml文件中判断
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        List<Category> categories = categoryService.getCategoryList();//查询所有博客分类
        Map<String, Object> map = new HashMap<>();
        map.put("blogs", pageInfo);
        map.put("categories", categories);
        return Result.ok("请求成功", map);
    }


    @GetMapping("/categoryAndTag")
    public Result categoryAndTag(){
        List<Category> categories = categoryService.getCategoryList();
        List<Tag> tags = tagService.getTagList();
        Map<String, Object> map = new HashMap<>();
        map.put("categories", categories);
        map.put("tags", tags);
        return Result.ok("请求成功", map);
    }
    /**
     *  查看博客，根据id
     * @param id
     * @return
     */
    @GetMapping("/blog")
    public Result blog(@RequestParam String id){
       Blog data= blogService.getBlogById(id);
       return Result.ok("请求成功",data);
    }

    //删除关联表
    @DeleteMapping("/blog")
    public Result DeleteBlog(@RequestParam String id){
        blogService.DeleteBlogById(id);
        return Result.ok("删除成功");
    }
    @PutMapping("/updateblog")
    public Result updateblog(@RequestBody com.cz.blogtwo.model.dto.Blog blog){
        return getResult(blog, "update");
    }
    @PostMapping("/saveblog")
    public Result saveBlog(@RequestBody com.cz.blogtwo.model.dto.Blog blog){
        return getResult(blog, "save");
    }
    /**
     * 更新博客推荐状态
     * @param id  博客id
     * @param recommend 是否推荐
     * @return
     */
    @PutMapping("/blog/recommend")
    public Result updateRecommend(@RequestParam Long id, @RequestParam Boolean recommend) {
        blogService.updateBlogRecommendById(id, recommend);
        return Result.ok("操作成功");
    }
    /**
     * 更新博客可见性
     *
     * @param id  博客id
     * @param blogVisibility 可见性相关DTO（传输过来的）
     * @return
     */
    @PutMapping("/blog/{id}/visibility")
    public Result updateBlogVisibilityById(@PathVariable Long id, @RequestBody BlogVisibility blogVisibility) {
        blogService.updateBlogVisibilityById(id, blogVisibility);
        return Result.ok("操作成功");
    }

    /**
     * 更新博客置顶状态
     *
     * @param id  博客id
     * @param top 是否置顶
     * @return
     */

    @PutMapping("/blog/top")
    public Result updateTop(@RequestParam Long id, @RequestParam Boolean top) {
        blogService.updateBlogTopById(id, top);
        return Result.ok("操作成功");
    }

    /**
     * 执行博客添加或更新操作：校验参数是否合法，添加分类、标签，维护博客标签关联表
     *
     * @param blog 博客文章DTO
     * @param type 添加或更新
     * @return
     */
    private Result getResult(com.cz.blogtwo.model.dto.Blog blog, String type) {
        //验证普通字段
        if (StringUtils.isEmpty(blog.getTitle(), blog.getFirstPicture(), blog.getContent(), blog.getDescription())
                || blog.getWords() == null || blog.getWords() < 0) {
            return Result.error("参数有误");
        }

        //处理分类
        Object cate = blog.getCate();
        if (cate == null) {
            return Result.error("分类不能为空");
        }
        if (cate instanceof Integer) {//选择了已存在的分类
            Category c = categoryService.getCategoryById(((Integer) cate).longValue());
            blog.setCategory(c);
        } else if (cate instanceof String) {//添加新分类
            //查询分类是否已存在
            Category category = categoryService.getCategoryByName((String) cate);
            if (category != null) {
                return Result.error("不可添加已存在的分类");
            }
            Category c = new Category();
            c.setName((String) cate);
            categoryService.saveCategory(c);
            blog.setCategory(c);
        } else {
            return Result.error("分类不正确");
        }

        //处理标签
        List<Object> tagList = blog.getTagList();
        List<Tag> tags = new ArrayList<>();
        for (Object t : tagList) {
            if (t instanceof Integer) {//选择了已存在的标签
                Tag tag = tagService.getTagById(((Integer) t).longValue());
                tags.add(tag);
            } else if (t instanceof String) {//添加新标签
                //查询标签是否已存在
                Tag tag1 = tagService.getTagByName((String) t);
                if (tag1 != null) {
                    return Result.error("不可添加已存在的标签");
                }
                Tag tag = new Tag();
                tag.setName((String) t);
                tagService.saveTag(tag);
                tags.add(tag);
            } else {
                return Result.error("标签不正确");
            }
        }

        Date date = new Date();
        if (blog.getReadTime() == null || blog.getReadTime() < 0) {
            blog.setReadTime((int) Math.round(blog.getWords() / 200.0));//粗略计算阅读时长
        }
        if (blog.getViews() == null || blog.getViews() < 0) {
            blog.setViews(0);
        }
        if ("save".equals(type)) {
            blog.setCreateTime(date);
            blog.setUpdateTime(date);
            User user = new User();
            user.setId((long) 1);//个人博客默认只有一个作者
            blog.setUser(user);

            blogService.saveBlog(blog);
            //关联博客和标签(维护 blog_tag 表)
            for (Tag t : tags) {
                blogService.saveBlogTag(blog.getId(), t.getId());
            }
            return Result.ok("添加成功");
        } else {
            blog.setUpdateTime(date);
            blogService.updateBlog(blog);
            //关联博客和标签(维护 blog_tag 表)
            blogService.deleteBlogTagByBlogId(blog.getId());
            for (Tag t : tags) {
                blogService.saveBlogTag(blog.getId(), t.getId());
            }
            return Result.ok("更新成功");
        }
    }
}
