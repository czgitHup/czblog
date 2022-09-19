package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginLogMapper {
    List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

    int deleteLoginLogById(Long id);

}
