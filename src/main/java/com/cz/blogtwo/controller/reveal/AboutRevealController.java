package com.cz.blogtwo.controller.reveal;

import com.cz.blogtwo.service.AboutService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutRevealController {
    @Autowired
    AboutService aboutService;

    /**
     * 获取关于我页面信息
     *
     * @return
     */
    @GetMapping("/about")
    public Result about() {
        return Result.ok("获取成功", aboutService.getAboutInfo());
    }
}
