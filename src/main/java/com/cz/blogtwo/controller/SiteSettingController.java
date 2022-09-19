package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.SiteSetting;
import com.cz.blogtwo.service.SiteSettingService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class SiteSettingController {
    @Autowired
    private SiteSettingService siteSettingService;

    /**
     * 获取所有站点配置信息
     * @return Map<String, List<SiteSetting>> typeMap  type1为基础设置 type2页脚徽标 type3资料卡
     */
    @GetMapping("/siteSettings")
    public Result siteSettings() {
        Map<String, List<SiteSetting>> typeMap = siteSettingService.getList();
        return Result.ok("请求成功", typeMap);
    }

    /**
     * 修改、删除(部分配置可为空，但不可删除)、添加(只能添加部分)站点配置
     *
     * @param map 包含所有站点信息更新后的数据 map => {settings=[更新后的所有配置List], deleteIds=[要删除的配置id List]}
     * @return
     */
    @PostMapping("/siteSettings")
    public Result updateAll(@RequestBody Map<String, Object> map) {
        List<LinkedHashMap> siteSettings = (List<LinkedHashMap>) map.get("settings");
        List<Integer> deleteIds = (List<Integer>) map.get("deleteIds");
        siteSettingService.updateSiteSetting(siteSettings, deleteIds);
        return Result.ok("更新成功");
    }


}
