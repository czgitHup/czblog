package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Friend;
import com.cz.blogtwo.model.vo.FriendInfo;

import java.util.List;

public interface FriendService {
    List<Friend> getFriendList();


    FriendInfo getFriendInfo(boolean cache, boolean md);

    void insertFriend(Friend friend);

    void setFriendInfoContent(String content);

    void setFriendInfoCommentEnabled(Boolean commentEnabled);

    void deleteFriend(Long id);

    void updateFriend(Friend friend);

    List<com.cz.blogtwo.model.vo.Friend> getFriendVOList();

}
