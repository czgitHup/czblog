package com.cz.blogtwo.controller;

import com.cz.blogtwo.entity.Friend;
import com.cz.blogtwo.service.FriendService;
import com.cz.blogtwo.utils.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @GetMapping("/friends")
    public Result friends(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy = " create_time desc";//创建时间降序
        PageHelper.startPage(pageNum, pageSize, orderBy);//分页查询的条件
        List<Friend> friends=friendService.getFriendList();
        PageInfo<Friend> pageInfo = new PageInfo<>(friends);
        return Result.ok("请求成功", pageInfo);
    }

    /**
     *  查询友情链接页面的信息
     * @return
     */
    @GetMapping("/friendInfo")
    public Result getFriendInfo() {
        return Result.ok("请求成功", friendService.getFriendInfo(false, false));
    }


    @PostMapping("/friend")
    public Result saveFriend(@RequestBody Friend friend) {
        friendService.insertFriend(friend);
        return Result.ok("添加成功");
    }
    @PutMapping("/friend")
    public Result updateFriend(@RequestBody Friend friend) {
        friendService.updateFriend(friend);
        return Result.ok("修改成功");
    }

    @PutMapping("/friendInfo/content")
    public Result setFriendInfoContent(@RequestBody Map map) {
        friendService.setFriendInfoContent((String)map.get("content"));
        return Result.ok("更改友情链接页面信息成功");
    }

    @PutMapping("/friendInfo/commentEnabled")
    public Result setFriendInfoCommentEnabled(@RequestParam Boolean commentEnabled) {
        friendService.setFriendInfoCommentEnabled(commentEnabled);
        return Result.ok("更改友情链接页面评论开关成功");
    }

    @DeleteMapping("/friend")
    public Result deleteFriend(@RequestParam Long id) {
        friendService.deleteFriend(id);
        return Result.ok("删除成功");
    }



}
