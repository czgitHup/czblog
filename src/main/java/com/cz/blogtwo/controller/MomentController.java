package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.Moment;
import com.cz.blogtwo.service.MomentService;
import com.cz.blogtwo.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MomentController {
    @Autowired
    private MomentService momentService;
    /**
     *  查询所有动态
     * @param pageNum 第几页
     * @param pageSize 每页几条数据
     * @return
     */
    @GetMapping("/moments")
    public Result tags(@RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = " create_time desc";//创建时间降序
        PageHelper.startPage(pageNum, pageSize, orderBy);//分页查询的条件
        List<Moment> moments=momentService.getMomentList();
        PageInfo<Moment> pageInfo = new PageInfo<>(moments);
        return Result.ok("请求成功", pageInfo);
    }
    @GetMapping("/moment")
    public Result getMomentById(@RequestParam String id){
        Moment data=momentService.getMomentById(id);
        return Result.ok("请求成功",data);

    }
    @PostMapping("/moment")
    public Result InsertMoment(@RequestBody Moment moment){
        if (moment.getCreateTime()==null){
            return Result.error("请求shibai");
        }
        if ("".equals(moment.getContent())){
            return Result.error("请求shibai");
        }
        momentService.InsertMoment(moment);
        return Result.ok("请求成功");

    }
    @PutMapping("/moment")
    public Result updateMoment(@RequestBody Moment moment){
        momentService.updateMoment(moment);
        return Result.ok("请求成功");
    }
    @DeleteMapping("/moment")
    public Result deleteMoment(@RequestParam String id){
        momentService.deleteMomentById(id);
        return Result.ok("请求成功");
    }
}
