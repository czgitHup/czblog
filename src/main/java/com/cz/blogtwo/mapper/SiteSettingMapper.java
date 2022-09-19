package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.SiteSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SiteSettingMapper {


    List<SiteSetting> getList();
    int saveSiteSetting(SiteSetting siteSetting);

    int updateSiteSetting(SiteSetting siteSetting);

    int deleteSiteSettingById(Integer id);

    List<SiteSetting> getFriendInfo();

    Integer setFriendInfoContent(String content);

    Integer setFriendInfoCommentEnabled(Boolean commentEnabled);
}
