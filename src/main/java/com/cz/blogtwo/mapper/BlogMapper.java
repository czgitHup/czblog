package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Blog;
import com.cz.blogtwo.model.dto.BlogVisibility;
import com.cz.blogtwo.model.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客
 */
@Mapper
@Repository
public interface BlogMapper {

    List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

    Blog getBlogById(String id);

    Integer saveBlog(com.cz.blogtwo.model.dto.Blog blog);

    Integer saveBlogTag(Long blogId, Long tagId);

    Integer updateBlog(com.cz.blogtwo.model.dto.Blog blog);

    Integer deleteBlogTagByBlogId(Long id);

    Integer updateBlogRecommendById(Long id, Boolean recommend);

    Integer updateBlogVisibilityById(Long id, BlogVisibility blogVisibility);

    Integer updateBlogTopById(Long id, Boolean top);

    Integer deleteBlogById(String id);

    List<Blog> getIdAndTitleList();


    int countBlog();

    List<CategoryBlogCount> getCategoryBlogCountList();

    List<NewBlog> getNewBlogListByIsPublished();

    List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(int randomBlogLimitNum);

    List<BlogInfo> getBlogInfoListByIsPublished();

    List<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName);

    BlogDetail getBlogByIdAndIsPublished(String id);

    List<String> getGroupYearMonthByIsPublished();

    List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String s);

    Integer countBlogByIsPublished();

    List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query);
}
