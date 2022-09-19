package com.cz.blogtwo.service;

import java.util.Map;

public interface AboutService {
    Map<String, String> getAboutList();

    void updateAbout(Map<String, String> map);

    Map<String, String> getAboutInfo();
}
