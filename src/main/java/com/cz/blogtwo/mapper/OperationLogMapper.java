package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OperationLogMapper {
    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);

    Integer deleteOperationLogById(Long id);
}
