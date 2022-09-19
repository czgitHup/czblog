package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.ExceptionLog;

import java.util.List;

public interface ExceptionLogService {
    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

    void deleteExceptionLogById(Long id);
}
