package com.cz.blogtwo.mapper;

import com.cz.blogtwo.entity.Moment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MomentMapper {
    List<Moment> getMomentList();

    Moment getMomentById(String id);

    Integer InsertMoment(Moment moment);

    Integer updateMoment(Moment moment);

    Integer deleteMomentById(String id);

    int addLikeByMomentId(Long id);
}
