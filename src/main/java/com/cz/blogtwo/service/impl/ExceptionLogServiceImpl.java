package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.ExceptionLog;
import com.cz.blogtwo.mapper.ExceptionLogMapper;
import com.cz.blogtwo.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
public class ExceptionLogServiceImpl implements ExceptionLogService {
    @Autowired
    private ExceptionLogMapper exceptionLogMapper;

    @Override
    public List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate) {
        return exceptionLogMapper.getExceptionLogListByDate(startDate, endDate);
    }

    @Transactional
    @Override
    public void deleteExceptionLogById(Long id) {
        if (exceptionLogMapper.deleteExceptionLogById(id) != 1) {
            //throw new PersistenceException("删除日志失败");
        }
    }
}
