package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Blog;
import com.cz.blogtwo.mapper.BlogMapper;
import com.cz.blogtwo.mapper.TagMapper;
import com.cz.blogtwo.model.dto.BlogVisibility;
import com.cz.blogtwo.model.vo.*;
import com.cz.blogtwo.service.BlogService;
import com.cz.blogtwo.service.TagService;
import com.cz.blogtwo.utils.markdown.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagService tagService;


    //随机博客显示5条
    private static final int randomBlogLimitNum = 5;
    //最新推荐博客显示3条
    private static final int newBlogPageSize = 3;
    //每页显示5条博客简介
    private static final int pageSize = 5;
    //博客简介列表排序方式
    private static final String orderBy = "is_top desc, create_time desc";
    //私密博客提示
    private static final String PRIVATE_BLOG_DESCRIPTION = "此文章受密码保护！";
    /**
     *   查询博客
     * @param title 标签信息
     * @param categoryId 分类id
     * @return
     */
    @Override
    public List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId) {
        return blogMapper.getListByTitleAndCategoryId(title,categoryId);

    }

    @Override
    public Blog getBlogById(String id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public void saveBlogTag(Long blogId, Long tagId) {
        Integer row =blogMapper.saveBlogTag(blogId, tagId);
    }

    @Override
    public void deleteBlogTagByBlogId(Long id) {
        Integer row =blogMapper.deleteBlogTagByBlogId(id);
    }

    @Override
    public void updateBlog(com.cz.blogtwo.model.dto.Blog blog) {
        Integer row = blogMapper.updateBlog(blog);
    }

    @Override
    public void saveBlog(com.cz.blogtwo.model.dto.Blog blog) {
        Integer row =blogMapper.saveBlog(blog);
    }

    @Transactional
    @Override
    public void DeleteBlogById(String id) {
        blogMapper.deleteBlogTagByBlogId(Long.valueOf(id));
        Integer row =blogMapper.deleteBlogById(id);
    }

    @Override
    public void updateBlogRecommendById(Long id, Boolean recommend) {
        Integer row=blogMapper.updateBlogRecommendById(id,recommend);
    }

    @Override
    public void updateBlogVisibilityById(Long id, BlogVisibility blogVisibility) {
        Integer row=blogMapper.updateBlogVisibilityById(id,blogVisibility);
    }

    @Override
    public List<Blog> getIdAndTitleList() {
        return blogMapper.getIdAndTitleList();
    }



    @Override
    public void updateBlogTopById(Long id, Boolean top) {
        Integer row=blogMapper.updateBlogTopById(id,top);
    }


    @Override
    public List<NewBlog> getNewBlogListByIsPublished() {
        PageHelper.startPage(1, newBlogPageSize);
        List<NewBlog> newBlogList = blogMapper.getNewBlogListByIsPublished();
        for (NewBlog newBlog : newBlogList) {
            if (!"".equals(newBlog.getPassword())) {
                newBlog.setPrivacy(true);
                newBlog.setPassword("");
            } else {
                newBlog.setPrivacy(false);
            }
        }
        //redisService.saveListToValue(redisKey, newBlogList);
        return newBlogList;
    }

    @Override
    public List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend() {
        List<RandomBlog> randomBlogs = blogMapper.
                getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(randomBlogLimitNum);
        for (RandomBlog randomBlog : randomBlogs) {
            if (!"".equals(randomBlog.getPassword())) {
                randomBlog.setPrivacy(true);
                randomBlog.setPassword("");
            } else {
                randomBlog.setPrivacy(false);
            }
        }
        return randomBlogs;
    }

    @Override
    public BlogDetail getBlogByIdAndIsPublished(String id) {
        BlogDetail blog = blogMapper.getBlogByIdAndIsPublished(id);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        //将浏览量设置为Redis中的最新值
        //int view = (int) redisService.getValueByHashKey(RedisKeyConstants.BLOG_VIEWS_MAP, blog.getId());
        blog.setViews(blog.getViews());
        return blog;
    }

    @Override
    public Map<String, Object> getArchiveBlogAndCountByIsPublished() {
        Map<String, Object> map = new HashMap<>();
        List<String> groupYearMonth = blogMapper.getGroupYearMonthByIsPublished();//查询公开博客年月List
        Map<String, List<ArchiveBlog>> archiveBlogMap = new LinkedHashMap<>();
        for (String s : groupYearMonth) {//s 是某个日期
            List<ArchiveBlog> archiveBlogs = blogMapper.getArchiveBlogListByYearMonthAndIsPublished(s);//按年月查询公开博客简要信息List
            for (ArchiveBlog archiveBlog : archiveBlogs) {//将某个日期下的博客设置隐私
                if (!"".equals(archiveBlog.getPassword())) {
                    archiveBlog.setPrivacy(true);//博客有密码，就不能查看
                    archiveBlog.setPassword("");
                } else {
                    archiveBlog.setPrivacy(false);//密码不为空，则将私密性设置为false
                }
            }
            archiveBlogMap.put(s, archiveBlogs);
        }
        Integer count = countBlogByIsPublished();//查询公开博客总数
        map.put("blogMap", archiveBlogMap);
        map.put("count", count);
        //redisService.saveMapToValue(redisKey, map);
        return map;
    }

    @Override
    public Integer countBlogByIsPublished() {//查询公开博客总数
        return blogMapper.countBlogByIsPublished();
    }
    @Override
    public List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query) {

        List<SearchBlog> searchBlogs = blogMapper.getSearchBlogListByQueryAndIsPublished(query);//模糊查询所有query字段的博客
        for (SearchBlog searchBlog : searchBlogs) {
            String content = searchBlog.getContent();//获取文章内容
            int contentLength = content.length();//获取文章的长度
            int index = content.indexOf(query) - 10;//返回指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
            index = index < 0 ? 0 : index;
            int end = index + 21;//以关键字字符串为中心返回21个字
            end = end > contentLength - 1 ? contentLength - 1 : end;
            searchBlog.setContent(content.substring(index, end));//设置展示的文章
        }
        return searchBlogs;
    }


    @Override
    public PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize, orderBy);//下一个语句就会包含此分页条件
        //查询所有分类下的公开博客博客
        List<BlogInfo> blogInfoListByCategoryNameAndIsPublished = blogMapper.getBlogInfoListByCategoryNameAndIsPublished(categoryName);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogInfoListByCategoryNameAndIsPublished);
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        //总页数，和数据
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        //将pageResult中博客对象的浏览量设置为Redis中的最新值
        //setBlogViewsFromRedisToPageResult(pageResult);
        return pageResult;
    }



    @Override
    public PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<BlogInfo> blogInfos = processBlogInfosPassword(blogMapper.getBlogInfoListByIsPublished());
        PageInfo<BlogInfo> pageInfo = new PageInfo<>(blogInfos);
        PageResult<BlogInfo> pageResult = new PageResult<>(pageInfo.getPages(), pageInfo.getList());
        //设置redis
        // setBlogViewsFromRedisToPageResult(pageResult);
        //添加首页缓存
        //redisService.saveKVToHash(redisKey, pageNum, pageResult);
        return pageResult;
    }




    //将有密码的博客设置为私密文章，没密码的展示，放到集合中
    private List<BlogInfo> processBlogInfosPassword(List<BlogInfo> blogInfos) {
        for (BlogInfo blogInfo : blogInfos) {
            if (!"".equals(blogInfo.getPassword())) {//有密码
                blogInfo.setPrivacy(true);//设置为私密文章
                blogInfo.setPassword("");
                blogInfo.setDescription(PRIVATE_BLOG_DESCRIPTION);//描述设置为收到密码保护，外界查看不到描述
            } else {
                blogInfo.setPrivacy(false);
                blogInfo.setDescription(MarkdownUtils.markdownToHtmlExtensions(blogInfo.getDescription()));//增加描述
            }
            blogInfo.setTags(tagService.getTagListByBlogId(blogInfo.getId()));
        }
        return blogInfos;
    }
}
