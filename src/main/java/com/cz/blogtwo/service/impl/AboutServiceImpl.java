package com.cz.blogtwo.service.impl;

import com.cz.blogtwo.entity.About;
import com.cz.blogtwo.mapper.AboutMapper;
import com.cz.blogtwo.service.AboutService;
import com.cz.blogtwo.utils.markdown.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Repository
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutMapper aboutMapper;

    @Override
    public Map<String, String> getAboutList() {
        List<About> abouts=aboutMapper.getAboutList();
        HashMap<String, String> map = new HashMap<>();
        for (About about : abouts) {
            map.put(about.getNameEn(),about.getValue());
        }
        return map;


    }
    @Override
    public Map<String, String> getAboutInfo() {

        List<About> abouts = aboutMapper.getList();//所有关于我的信息
        Map<String, String> aboutInfoMap = new HashMap<>();
        for (About about : abouts) {
            if ("content".equals(about.getNameEn())) {//正文
                about.setValue(MarkdownUtils.markdownToHtmlExtensions(about.getValue()));//增加拓展
            }
            aboutInfoMap.put(about.getNameEn(), about.getValue());
        }
        return aboutInfoMap;

    }


    @Transactional
    @Override
    public void updateAbout(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            updateOneAbout(key, map.get(key));
        }
    }

    //事务回滚
    @Transactional
    public void updateOneAbout(String nameEn, String value) {
        if (aboutMapper.updateAbout(nameEn, value) != 1) {
            //throw new PersistenceException("修改失败");
        }
    }
}
