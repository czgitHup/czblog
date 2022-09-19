package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.LoginLog;
import com.cz.blogtwo.mapper.LoginLogMapper;
import com.cz.blogtwo.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLog> getLoginLogListByDate(String startDate, String endDate) {
        return loginLogMapper.getLoginLogListByDate(startDate, endDate);
    }

    @Transactional
    @Override
    public void deleteLoginLogById(Long id) {
        if (loginLogMapper.deleteLoginLogById(id) != 1) {
            //throw new PersistenceException("删除日志失败");
        }
    }
}
