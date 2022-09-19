package com.cz.blogtwo.controller.reveal;

import com.cz.blogtwo.model.vo.Friend;
import com.cz.blogtwo.model.vo.FriendInfo;
import com.cz.blogtwo.service.FriendService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FriendRevealController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/friends")
    public Result getFriend(){
        List<Friend> friendList = friendService.getFriendVOList();
        FriendInfo friendInfo = friendService.getFriendInfo(true, true);//查询友情链接的内容及评论
        Map<String, Object> map = new HashMap<>();
        map.put("friendList", friendList);
        map.put("friendInfo", friendInfo);
        return Result.ok("获取成功", map);
    }
}
