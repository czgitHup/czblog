package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendMapper {

    List<Friend> getFriendList();

    Integer insertFriend(Friend friend);

    int deleteFriend(Long id);

    Integer updateFriend(Friend friend);

    List<com.cz.blogtwo.model.vo.Friend> getFriendVOList();

}
