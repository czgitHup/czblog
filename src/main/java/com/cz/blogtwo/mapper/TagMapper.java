package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Tag;
import com.cz.blogtwo.model.vo.TagBlogCount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    List<Tag> getTagList();

    Tag getTagById(long id);

    Tag getTagByName(String name);

    Integer saveTag(Tag tag);

    Integer updateTag(Tag tag);

    Integer deleteTagById(String id);

    List<TagBlogCount> getTagBlogCount();

    List<Tag> getTagListNotId();

    List<Tag> getTagListByBlogId(Long blogId);
}
