package com.rainier.service;

import com.rainier.model.Url;
import com.rainier.util.Result;

import java.util.List;
import java.util.Map;

public interface UrlService {
    int insertUrl(String fileName, String path, String url);

    List<Url> selectShipin();

    void quartzDeleteImgUrl();

    void deleteFile();

    Result deleteFile(Map map);
}
