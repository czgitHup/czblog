package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTagList();

    Tag getTagById(long id);

    Tag getTagByName(String name);

    void saveTag(Tag tag);

    void updateTag(Tag tag);

    void deleteTagById(String id);

    List<Tag> getTagListNotId();

    List<Tag> getTagListByBlogId(Long id);
}
