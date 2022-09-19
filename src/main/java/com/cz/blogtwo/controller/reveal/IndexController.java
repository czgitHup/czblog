package com.cz.blogtwo.controller.reveal;

import com.cz.blogtwo.entity.Category;
import com.cz.blogtwo.entity.Tag;
import com.cz.blogtwo.model.vo.NewBlog;
import com.cz.blogtwo.model.vo.RandomBlog;
import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.service.CategoryService;
import com.cz.blogtwo.service.SiteSettingService;
import com.cz.blogtwo.service.TagService;
import com.cz.blogtwo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 站点相关
 */

@RestController
public class IndexController {
	@Autowired
	SiteSettingService siteSettingService;
	@Autowired
	BlogService blogService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	TagService tagService;

	/**
	 * 获取站点配置信息、最新推荐博客、分类列表、标签云、随机博客
	 *
	 * @return
	 */
	@GetMapping("/site")
	public Result site() {
		Map<String, Object> map = siteSettingService.getSiteInfo();//获得获取站点配置信息
		List<NewBlog> newBlogList = blogService.getNewBlogListByIsPublished();//最新推荐博客
		List<Category> categoryList = categoryService.getCategoryNameList();//获得分类列表
		List<Tag> tagList = tagService.getTagListNotId();//获得标签云
		List<RandomBlog> randomBlogList =
				blogService.getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();//随机博客

		map.put("newBlogList", newBlogList);
		map.put("categoryList", categoryList);
		map.put("tagList", tagList);
		map.put("randomBlogList", randomBlogList);
		return Result.ok("请求成功", map);
	}
}
