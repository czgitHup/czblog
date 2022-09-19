package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.VisitLog;
import com.cz.blogtwo.model.dto.VisitLogUuidTime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VisitLogMapper {
    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTime> getUUIDAndCreateTimeByYesterday();

    int saveVisitLog(VisitLog log);

    int deleteVisitLogById(Long id);

    int countVisitLogByToday();


}
