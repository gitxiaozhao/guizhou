package com.rainier.service;

import com.rainier.model.Content;
import com.rainier.util.Result;

import java.util.Map;

public interface ContentService {
    Result addContent(Map map);

    Result getContentList(Map map);

    Result deleteContent(Map map);

    Result getContentById(Map map);
}
