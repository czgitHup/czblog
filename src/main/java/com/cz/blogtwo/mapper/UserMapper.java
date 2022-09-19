package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 关于我
 */
@Mapper
@Repository
public interface UserMapper {

    User findByUsername(String username);

    User findById(Long id);
}
