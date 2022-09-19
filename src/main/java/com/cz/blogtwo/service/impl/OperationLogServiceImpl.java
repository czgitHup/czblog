package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.OperationLog;
import com.cz.blogtwo.mapper.OperationLogMapper;
import com.cz.blogtwo.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    private OperationLogMapper operationLogMapper;
    @Override
    public List<OperationLog> getOperationLogListByDate(String startDate, String endDate) {
        return operationLogMapper.getOperationLogListByDate(startDate, endDate);
    }

    @Override
    public void deleteOperationLogById(Long id) {
        Integer row =operationLogMapper.deleteOperationLogById(id);
    }
}
