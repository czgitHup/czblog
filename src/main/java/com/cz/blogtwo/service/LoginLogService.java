package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.LoginLog;

import java.util.List;

public interface LoginLogService {
    List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

    void deleteLoginLogById(Long id);

}
