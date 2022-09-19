package com.cz.blogtwo.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 友链页面信息
 * @Author: Naccl
 * @Date: 2020-09-09
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FriendInfo {
	private String content;//内容
	private Boolean commentEnabled;//评论是否启用
}
