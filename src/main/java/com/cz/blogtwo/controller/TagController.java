package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.Category;
import com.cz.blogtwo.entity.Tag;
import com.cz.blogtwo.service.TagService;
import com.cz.blogtwo.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags")
    public Result tags(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//分页查询的条件
        List<Tag> tags=tagService.getTagList();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        return Result.ok("请求成功", pageInfo);
    }
    @PostMapping("/tag")
    public Result InsertTag(@RequestBody Tag tag){
        tagService.saveTag(tag);
        return Result.ok("请求成功");
    }
    @PutMapping("/tag")
    public Result updateTag(@RequestBody Tag tag){
        tagService.updateTag(tag);
        return Result.ok("请求成功");
    }
    @DeleteMapping("/tag")
    public Result deleteTag(@RequestParam String id){
        tagService.deleteTagById(id);
        return Result.ok("请求成功");
    }
}
