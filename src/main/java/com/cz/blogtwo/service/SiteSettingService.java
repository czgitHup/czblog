package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.SiteSetting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SiteSettingService {
    Map<String, List<SiteSetting>> getList();

    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);

    Map<String, Object> getSiteInfo();
}
