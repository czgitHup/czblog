package com.cz.blogtwo.service;

import com.cz.blogtwo.entity.Moment;

import java.util.List;

public interface MomentService {
    List<Moment> getMomentList();

    Moment getMomentById(String id);

    void InsertMoment(Moment moment);

    void updateMoment(Moment moment);

    void deleteMomentById(String id);

    List<Moment> getMomentVOList(Integer pageNum, boolean adminIdentity);

    void addLikeByMomentId(Long id);
}
