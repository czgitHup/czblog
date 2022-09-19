package com.cz.blogtwo.controller.reveal;

import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ArchiveRevealController {
    @Autowired
    private BlogService blogService;



    /**
     * 按年月分组归档公开博客 统计公开博客总数
     *
     * @return
     */
    @GetMapping("/archives")
    public Result archives() {
        Map<String, Object> archiveBlogMap = blogService.getArchiveBlogAndCountByIsPublished();
        return Result.ok("请求成功", archiveBlogMap);
    }
}
