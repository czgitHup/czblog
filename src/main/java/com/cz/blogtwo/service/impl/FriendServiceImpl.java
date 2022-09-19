package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Friend;
import com.cz.blogtwo.entity.SiteSetting;
import com.cz.blogtwo.mapper.FriendMapper;
import com.cz.blogtwo.mapper.SiteSettingMapper;
import com.cz.blogtwo.model.vo.FriendInfo;
import com.cz.blogtwo.service.FriendService;
import com.cz.blogtwo.utils.markdown.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Repository
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private SiteSettingMapper siteSettingMapper;

    @Override
    public List<Friend> getFriendList() {

        return friendMapper.getFriendList();
    }

    @Override
    public void insertFriend(Friend friend) {
        friend.setViews(0);
        friend.setCreateTime(new Date());
        Integer row =friendMapper.insertFriend(friend);
    }

    @Override
    public void updateFriend(Friend friend) {
        Integer row =friendMapper.updateFriend(friend);
    }

    @Override
    public void setFriendInfoContent(String content) {
        Integer row = siteSettingMapper.setFriendInfoContent(content);
    }

    @Override
    public void setFriendInfoCommentEnabled(Boolean commentEnabled) {
        Integer row = siteSettingMapper.setFriendInfoCommentEnabled(commentEnabled);
    }




    @Override
    public void deleteFriend(Long id) {
        int row = friendMapper.deleteFriend(id);
    }

    @Override
    public FriendInfo getFriendInfo(boolean cache, boolean md) {
        List<SiteSetting> siteSettings = siteSettingMapper.getFriendInfo();
        FriendInfo friendInfo = new FriendInfo();
        for (SiteSetting siteSetting : siteSettings) {
            if ("friendContent".equals(siteSetting.getNameEn())) {
                if (md) {//增加内容
                    friendInfo.setContent(MarkdownUtils.markdownToHtmlExtensions(siteSetting.getValue()));
                } else {
                    friendInfo.setContent(siteSetting.getValue());
                }
            } else if ("friendCommentEnabled".equals(siteSetting.getNameEn())) {
                if ("1".equals(siteSetting.getValue())) {
                    friendInfo.setCommentEnabled(true);
                } else {
                    friendInfo.setCommentEnabled(false);
                }
            }
        }
        return friendInfo;
    }


    @Override
    public List<com.cz.blogtwo.model.vo.Friend> getFriendVOList() {
        return friendMapper.getFriendVOList();
    }
}
