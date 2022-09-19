package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Comment;
import com.cz.blogtwo.model.vo.PageComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {


    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, long parentCommentId);

    Integer updateComment(Comment comment);

    /**
     * 根据父评论id查询所有子评论
     * @param parentCommentId
     * @return
     */
    List<Comment> getListByParentCommentId(String parentCommentId);

    Integer deleteCommentById(Long id);

    void updatePublished(Boolean published,String id);

    void updateNoticeById(Boolean notice, String id);

    int countComment();


    Integer countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);

    List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);
}
