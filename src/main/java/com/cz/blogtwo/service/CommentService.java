package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Comment;
import com.cz.blogtwo.model.vo.PageComment;

import java.util.List;

public interface CommentService {


    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, long l);

    void updateComment(Comment comment);

    void deleteCommentById(String id);

    void updatePublishedById(Boolean published,String id);

    void updateNoticeById(Boolean notice, String id);

    Integer countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished);


    List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId);
}
