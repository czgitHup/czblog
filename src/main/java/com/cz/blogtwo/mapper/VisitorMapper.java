package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VisitorMapper {
    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    int deleteVisitorById(Long id);

}
