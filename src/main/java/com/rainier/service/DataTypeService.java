package com.rainier.service;

import com.rainier.util.Result;

import java.util.Map;

public interface DataTypeService {
    Result addOrUpdateDateType(Map map);

    Result getDataType(Map map);

    Result deleteDataType(Map map);

    Result getDataTypeById(Map map);
}
