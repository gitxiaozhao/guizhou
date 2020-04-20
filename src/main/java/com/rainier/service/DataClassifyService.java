package com.rainier.service;

import com.rainier.util.Result;

import java.util.Map;

public interface DataClassifyService {
    Result addOrUpdateDataClassify(Map map);

    Result getDataClassify(Map map);

    Result deleteDataClassify(Map map);

    Result getDataClassifyById(Map map);
}
