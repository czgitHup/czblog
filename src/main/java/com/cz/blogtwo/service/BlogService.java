package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Blog;
import com.cz.blogtwo.model.dto.BlogVisibility;
import com.cz.blogtwo.model.vo.*;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

    Blog getBlogById(String id);

    void saveBlog(com.cz.blogtwo.model.dto.Blog blog);

    void saveBlogTag(Long id, Long id1);

    void updateBlog(com.cz.blogtwo.model.dto.Blog blog);

    void deleteBlogTagByBlogId(Long id);

    void updateBlogRecommendById(Long id, Boolean recommend);

    void updateBlogVisibilityById(Long id, BlogVisibility blogVisibility);

    void updateBlogTopById(Long id, Boolean top);

    void DeleteBlogById(String id);

    List<Blog> getIdAndTitleList();


    List<NewBlog> getNewBlogListByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend();

    PageResult<BlogInfo> getBlogInfoListByIsPublished(Integer pageNum);

    PageResult<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName, Integer pageNum);

    BlogDetail getBlogByIdAndIsPublished(String id);

    Map<String, Object> getArchiveBlogAndCountByIsPublished();
    Integer countBlogByIsPublished();

    List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String trim);
}
