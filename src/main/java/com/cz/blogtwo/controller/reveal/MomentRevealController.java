package com.cz.blogtwo.controller.reveal;


import com.cz.blogtwo.constant.JwtConstants;
import com.cz.blogtwo.entity.Moment;
import com.cz.blogtwo.entity.User;

import com.cz.blogtwo.model.vo.PageResult;
import com.cz.blogtwo.service.MomentService;
import com.cz.blogtwo.service.UserService;
import com.cz.blogtwo.service.impl.UserServiceImpl;
import com.cz.blogtwo.utils.JwtUtils;
import com.cz.blogtwo.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 动态
 * @Author: Naccl
 * @Date: 2020-08-25
 */
@RestController
public class MomentRevealController {
	@Autowired
	MomentService momentService;
	@Autowired
	UserServiceImpl userService;

	/**
	 * 分页查询动态List
	 *
	 * @param pageNum 页码
	 * @param jwt     博主访问Token
	 * @return
	 */

	@GetMapping("/moments")
	public Result moments(@RequestParam(defaultValue = "1") Integer pageNum,
						  @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
		boolean adminIdentity = false;
		if (JwtUtils.judgeTokenIsExist(jwt)) {//判断token是否存在
			try {
				String subject = JwtUtils.getTokenBody(jwt).getSubject();//根据jwt判断权限是否为admin:
				if (subject.startsWith(JwtConstants.ADMIN_PREFIX)) {//证明是博主
					//博主身份Token
					String username = subject.replace(JwtConstants.ADMIN_PREFIX, "");
					User admin = (User) userService.loadUserByUsername(username);//验证博主是否存在
					if (admin != null) {
						adminIdentity = true;//存在
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		PageInfo<Moment> pageInfo = new PageInfo<>(momentService.getMomentVOList(pageNum, adminIdentity));
		PageResult<Moment> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
		return Result.ok("获取成功", pageResult);
	}

	/**
	 * 给动态点赞
	 * 简单限制一下点赞
	 *
	 * @param id 动态id
	 * @return
	 */
	@PostMapping("/moment/like/{id}")
	public Result like(@PathVariable Long id) {
		momentService.addLikeByMomentId(id);
		return Result.ok("点赞成功");
	}
}
