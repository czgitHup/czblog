package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.Blog;
import com.cz.blogtwo.entity.Comment;
import com.cz.blogtwo.entity.Moment;
import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.service.CommentService;
import com.cz.blogtwo.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    /**
     * 按页面和博客id分页查询评论List
     * @param page     要查询的页面(博客文章or关于我...)
     * @param blogId   如果是博客文章页面 需要提供博客id
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/comments")
    public Result comments (@RequestParam(defaultValue = "") Integer page,
                            @RequestParam(defaultValue = "") Long blogId,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy = " create_time desc";//创建时间降序
        PageHelper.startPage(pageNum, pageSize, orderBy);//分页查询的条件
        List<Comment> comments = commentService.getListByPageAndParentCommentId(page, blogId, (long) -1);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        return Result.ok("请求成功", pageInfo);
    }

    /**
     * 修改评论
     * @param comment
     * @return
     */
    @PutMapping("/comment")
    public Result updateComment(@RequestBody Comment comment){
        commentService.updateComment(comment);
        return Result.ok("修改评论成功");
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/comment")
    public Result deleteCommentById(@RequestParam String id) {
        commentService.deleteCommentById(id);
        return Result.ok("评论删除成功");
    }

    /**
     * 获得博客页面所属id和Title
     * @return
     */
    @GetMapping("/blogIdAndTitle")
    public Result getBlogIdAndTitle(){
        List<Blog> blogs = blogService.getIdAndTitleList();
        return Result.ok("请求成功", blogs);
    }



    @PutMapping("/comment/published")
    public Result updatePublished(@RequestParam Boolean published,@RequestParam String id){
        commentService.updatePublishedById(published,id);
        return Result.ok("修改公开成功");
    }


    @PutMapping("/comment/notice")
    public Result updateNotice(@RequestParam Boolean notice,@RequestParam String id){
        commentService.updateNoticeById(notice,id);
        return Result.ok("修改邮件提醒成功");
    }




}
