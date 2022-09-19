package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.VisitLog;

import java.util.List;

public interface VisitLogService {
    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    void deleteVisitLogById(Long id);
}
