package com.cz.blogtwo.controller.reveal;


import com.cz.blogtwo.constant.JwtConstants;
import com.cz.blogtwo.entity.User;
import com.cz.blogtwo.enums.CommentOpenStateEnum;
import com.cz.blogtwo.model.dto.Comment;
import com.cz.blogtwo.model.vo.PageComment;
import com.cz.blogtwo.model.vo.PageResult;
import com.cz.blogtwo.service.CommentService;
import com.cz.blogtwo.service.impl.UserServiceImpl;
import com.cz.blogtwo.utils.JwtUtils;
import com.cz.blogtwo.utils.Result;
import com.cz.blogtwo.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 评论
 * @Author: Naccl
 * @Date: 2020-08-15
 */
@RestController
public class CommentRevealController {
	@Autowired
	CommentService commentService;
	@Autowired
	UserServiceImpl userService;

	/**
	 * 根据页面分页查询评论列表
	 *
	 * @param page     页面分类（0普通文章，1关于我...）
	 * @param blogId   如果page==0，需要博客id参数
	 * @param pageNum  页码
	 * @param pageSize 每页个数
	 * @param jwt      若文章受密码保护，需要获取访问Token
	 * @return
	 */
	@GetMapping("/comments")
	public Result comments(@RequestParam Integer page,
						   @RequestParam(defaultValue = "") Long blogId,
						   @RequestParam(defaultValue = "1") Integer pageNum,
						   @RequestParam(defaultValue = "10") Integer pageSize,
						   @RequestHeader(value = "Authorization", defaultValue = "") String jwt) {
//		CommentOpenStateEnum openState = commentUtils.judgeCommentState(page, blogId);
//		switch (openState) {
//			case NOT_FOUND:
//				return Result.create(404, "该博客不存在");
//			case CLOSE:
//				return Result.create(403, "评论已关闭");
//			case PASSWORD:
//				//文章受密码保护，需要验证Token
//				if (JwtUtils.judgeTokenIsExist(jwt)) {
//					try {
//						String subject = JwtUtils.getTokenBody(jwt).getSubject();
//						if (subject.startsWith(JwtConstants.ADMIN_PREFIX)) {
//							//博主身份Token
//							String username = subject.replace(JwtConstants.ADMIN_PREFIX, "");
//							User admin = (User) userService.loadUserByUsername(username);
//							if (admin == null) {
//								return Result.create(403, "博主身份Token已失效，请重新登录！");
//							}
//						} else {
//							//经密码验证后的Token
//							Long tokenBlogId = Long.parseLong(subject);
//							//博客id不匹配，验证不通过，可能博客id改变或客户端传递了其它密码保护文章的Token
//							if (!tokenBlogId.equals(blogId)) {
//								return Result.create(403, "Token不匹配，请重新验证密码！");
//							}
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//						return Result.create(403, "Token已失效，请重新验证密码！");
//					}
//				} else {
//					return Result.create(403, "此文章受密码保护，请验证密码！");
//				}
//				break;
//			default:
//				break;
//		}
		//查询该页面所有评论的数量
		Integer allComment = commentService.countByPageAndIsPublished(page, blogId, null);
		//查询该页面公开评论的数量
		Integer openComment = commentService.countByPageAndIsPublished(page, blogId, true);
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<PageComment> pageInfo = new PageInfo<>(commentService.getPageCommentList(page, blogId, (long) -1));
		PageResult<PageComment> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
		Map<String, Object> map = new HashMap<>();
		map.put("allComment", allComment);
		map.put("closeComment", allComment - openComment);
		map.put("comments", pageResult);
		return Result.ok("获取成功", map);
	}
}
