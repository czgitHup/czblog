package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.Moment;
import com.cz.blogtwo.mapper.MomentMapper;
import com.cz.blogtwo.service.MomentService;
import com.cz.blogtwo.utils.markdown.MarkdownUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class MomentServiceImpl implements MomentService {
    @Autowired
    private MomentMapper momentMapper;

    //每页显示5条动态
    private static final int pageSize = 5;
    //动态列表排序方式
    private static final String orderBy = "create_time desc";
    //私密动态提示
    private static final String PRIVATE_MOMENT_CONTENT = "<p>此条为私密动态，仅发布者可见！</p>";


    @Override
    public List<Moment> getMomentList() {
        return momentMapper.getMomentList();
    }

    @Override
    public Moment getMomentById(String id) {
        return momentMapper.getMomentById(id);
    }

    @Override
    public void InsertMoment(Moment moment) {
        Integer row = momentMapper.InsertMoment(moment);
    }

    @Override
    public void updateMoment(Moment moment) {
        Integer row = momentMapper.updateMoment(moment);
    }

    @Override
    public void deleteMomentById(String id) {
        Integer row = momentMapper.deleteMomentById(id);
    }

    @Override
    public List<Moment> getMomentVOList(Integer pageNum, boolean adminIdentity) {
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<Moment> moments = momentMapper.getMomentList();
        for (Moment moment : moments) {
            if (adminIdentity || moment.getPublished()) {
                moment.setContent(MarkdownUtils.markdownToHtmlExtensions(moment.getContent()));
            } else {
                moment.setContent(PRIVATE_MOMENT_CONTENT);
            }
        }
        return moments;
    }

    @Override
    public void addLikeByMomentId(Long id) {
        if (momentMapper.addLikeByMomentId(id) != 1) {
            //throw new PersistenceException("操作失败");
        }
    }
}

