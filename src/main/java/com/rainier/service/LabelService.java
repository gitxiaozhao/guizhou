package com.rainier.service;

import com.rainier.util.Result;

import java.util.Map;

public interface LabelService {
    Result addLabel(Map map);

    Result getLabel(Map map);

    Result deleteLabel(Map map);

    Result getAllLabel();

    Result getLabelById(Map map);
}
