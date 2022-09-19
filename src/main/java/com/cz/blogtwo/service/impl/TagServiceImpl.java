package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Tag;
import com.cz.blogtwo.mapper.TagMapper;
import com.cz.blogtwo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<Tag> getTagList() {
        return tagMapper.getTagList();
    }

    @Override
    public Tag getTagById(long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public void saveTag(Tag tag) {
        Integer row=tagMapper.saveTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        Integer row = tagMapper.updateTag(tag);
    }

    @Override
    public void deleteTagById(String id) {
        Integer row =tagMapper.deleteTagById(id);
    }

    @Override
    public List<Tag> getTagListNotId() {
        List<Tag> tagList = tagMapper.getTagListNotId();
        return tagList;
    }

    @Override
    public List<Tag> getTagListByBlogId(Long blogId) {
        return tagMapper.getTagListByBlogId(blogId);
    }
}
