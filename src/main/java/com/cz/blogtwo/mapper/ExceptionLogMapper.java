package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExceptionLogMapper {

    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

    int deleteExceptionLogById(Long id);
}
