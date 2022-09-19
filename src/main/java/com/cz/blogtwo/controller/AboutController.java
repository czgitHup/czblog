package com.cz.blogtwo.controller;

import com.cz.blogtwo.service.AboutService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @GetMapping("/about")
    public Result getAbout(){
        Map<String,String> data=aboutService.getAboutList();
        return Result.ok("查询成功",data);
    }

    @PutMapping("/about")
    public Result updateAbout(@RequestBody Map<String,String> map){
        aboutService.updateAbout(map);
        return Result.ok("修改成功");
    }
}
