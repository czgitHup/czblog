package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.About;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AboutMapper {
    List<About> getAboutList();
    int updateAbout(String nameEn, String value);

    List<About> getList();

}
