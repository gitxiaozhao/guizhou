package com.rainier.service;

import com.rainier.model.LaboratoryType;
import com.rainier.util.Result;

import java.util.Map;


public interface LaboratoryTypeService {
    Result addOrUpdateLaboratoryType(LaboratoryType laboratoryType);

    Result getLaboratoryTypeById(Map map);

    Result getLaboratoryTypeList(Map map);

    Result deleteLaboratoryType(Map map);

    Result getAllLaboratoryType();
}
