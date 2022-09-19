package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.VisitLog;
import com.cz.blogtwo.mapper.VisitLogMapper;
import com.cz.blogtwo.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class VisitLogServiceImpl implements VisitLogService {
    @Autowired
    private VisitLogMapper visitLogMapper;

    @Override
    public List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate) {
        return visitLogMapper.getVisitLogListByUUIDAndDate(uuid, startDate, endDate);
    }

    @Override
    public void deleteVisitLogById(Long id) {
        Integer row =visitLogMapper.deleteVisitLogById(id);
    }
}
