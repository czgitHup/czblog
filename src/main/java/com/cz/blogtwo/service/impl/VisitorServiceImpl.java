package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Visitor;
import com.cz.blogtwo.mapper.VisitorMapper;
import com.cz.blogtwo.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public List<Visitor> getVisitorListByDate(String startDate, String endDate) {
        return visitorMapper.getVisitorListByDate(startDate, endDate);
    }

    @Override
    public void deleteVisitor(Long id, String uuid) {
        //删除Redis中该访客的uuid
        //redisService.deleteValueBySet(RedisKeyConstants.IDENTIFICATION_SET, uuid);
        if (visitorMapper.deleteVisitorById(id) != 1) {
            //throw new PersistenceException("删除访客失败");
        }
    }
}
