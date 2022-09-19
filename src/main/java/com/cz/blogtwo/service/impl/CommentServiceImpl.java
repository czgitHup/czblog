package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Comment;
import com.cz.blogtwo.mapper.CommentMapper;
import com.cz.blogtwo.model.vo.PageComment;
import com.cz.blogtwo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Repository
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, long parentCommentId) {
        List<Comment> comments = commentMapper.getListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (Comment c : comments) {
            //递归查询子评论及其子评论
            List<Comment> replyComments = getListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }


    @Override
    public void updateComment(Comment comment) {
        Integer row =commentMapper.updateComment(comment);
    }

    @Override
    public void updatePublishedById(Boolean published,String id) {
        commentMapper.updatePublished(published,id);
    }

    @Override
    public void updateNoticeById(Boolean notice, String id) {
        commentMapper.updateNoticeById(notice,id);
    }

    @Override
    public Integer countByPageAndIsPublished(Integer page, Long blogId, Boolean isPublished) {
        return commentMapper.countByPageAndIsPublished(page, blogId, isPublished);
    }

    @Override
    public List<PageComment> getPageCommentList(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c : comments) {
            List<PageComment> tmpComments = new ArrayList<>();
            getReplyComments(tmpComments, c.getReplyComments());
            //对于两列评论来说，按时间顺序排列应该比树形更合理些
            //排序一下
            Comparator<PageComment> comparator = (c1, c2) -> c1.getCreateTime().compareTo(c2.getCreateTime());
            tmpComments.sort(comparator);

            c.setReplyComments(tmpComments);
        }
        return comments;
    }

    /**
     * 将所有子评论递归取出到一个List中
     *
     * @param comments
     * @return
     */
    private void getReplyComments(List<PageComment> tmpComments, List<PageComment> comments) {
        for (PageComment c : comments) {
            tmpComments.add(c);
            getReplyComments(tmpComments, c.getReplyComments());
        }
    }
    private List<PageComment> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId) {
        List<PageComment> comments = commentMapper.getPageCommentListByPageAndParentCommentId(page, blogId, parentCommentId);
        for (PageComment c : comments) {
            List<PageComment> replyComments = getPageCommentListByPageAndParentCommentId(page, blogId, c.getId());
            c.setReplyComments(replyComments);
        }
        return comments;
    }

    @Transactional
    @Override
    public void deleteCommentById(String id) {
        List<Comment> comments = getAllReplyComments(id);//将子评论装配到集合里
        //如果此评论没有评论，则直接删除。
        if (ListUtils.isEmpty(comments)){
            Integer row =commentMapper.deleteCommentById(Long.valueOf(id));
            System.out.println(row);
            return;
        }
        //有的话，则递归删除
        for (Comment c : comments) {
            delete(c);
        }

//        if (commentMapper.deleteCommentById(commentId) != 1) {
//            throw new PersistenceException("评论删除失败");
//        }
    }
    /**
     * 递归删除子评论
     *  传的是子评论
     * @param comment 需要删除子评论的父评论
     * @return
     */
    private void delete(Comment comment) {
        //如果此评论没有子评论则删除
        if (comment.getReplyComments()==null){
            Integer row =commentMapper.deleteCommentById(comment.getId());
            System.out.println(row);
        }
        //有的话则再次递归删除
        for (Comment c : comment.getReplyComments()) {
            delete(c);
        }
//        if (commentMapper.deleteCommentById(comment.getId()) != 1) {
//            throw new PersistenceException("评论删除失败");
//        }
    }

    /**
     * 按id递归查询子评论
     *
     * @param parentCommentId 需要查询子评论的父评论id
     * @return
     */
    private List<Comment> getAllReplyComments(String parentCommentId) {
        //根据父评论id查询此夫评论下的所有子评论
        List<Comment> comments = commentMapper.getListByParentCommentId(parentCommentId);
        if (comments==null){
            return null;
        }
        for (Comment c : comments) {
            //查询子评论下有没有其它子评论
            List<Comment> replyComments = getAllReplyComments(String.valueOf(c.getId()));
            c.setReplyComments(replyComments);
        }
        return comments;
    }

}
