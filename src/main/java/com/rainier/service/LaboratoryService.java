package com.rainier.service;

import com.rainier.model.Laboratory;
import com.rainier.util.Result;

import java.util.Map;

public interface LaboratoryService {
    Result addLaboratory(Laboratory laboratory);

    Result getLaboratoryList(Map map);

    Result getLaboratoryById(Laboratory laboratory);

    Result deleteLaboratoryByIds(Map map);

    Result getLaboratoryListByRecommend1(Map map);
}
