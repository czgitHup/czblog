package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Visitor;

import java.util.List;

public interface VisitorService {
    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    void deleteVisitor(Long id, String uuid);


}
