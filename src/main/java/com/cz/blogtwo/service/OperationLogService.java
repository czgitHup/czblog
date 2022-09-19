package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.OperationLog;

import java.util.List;

public interface OperationLogService {
    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);

    void deleteOperationLogById(Long id);
}
