package com.rainier.service.impl;

import com.rainier.mapper.LaboratoryTypeMapper;
import com.rainier.model.Laboratory;
import com.rainier.model.LaboratoryType;
import com.rainier.service.LaboratoryTypeService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LaboratoryTypeServiceImpl implements LaboratoryTypeService {

    @Autowired
    private LaboratoryTypeMapper laboratoryTypeMapper;

    @Override
    public Result addOrUpdateLaboratoryType(LaboratoryType laboratoryType) {
        if (laboratoryType.getId()!=null){
            laboratoryTypeMapper.updateLaboratoryType(laboratoryType);
            return Result.success("修改成功！");
        }else {
            laboratoryTypeMapper.addLaboratoryType(laboratoryType);
            return Result.success("添加成功！");
        }

    }

    @Override
    public Result getLaboratoryTypeById(Map map) {

        LaboratoryType laboratoryType = laboratoryTypeMapper.getLaboratoryTypeById(Integer.parseInt(map.get("id").toString()));
        return Result.success(laboratoryType);
    }

    @Override
    public Result getLaboratoryTypeList(Map map) {
        String key = map.get("key").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<LaboratoryType> page = new Page<LaboratoryType>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(laboratoryTypeMapper.queryLaboratoryTypeCount(key));
        page.setList(laboratoryTypeMapper.queryLaboratoryTypeList(key, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result deleteLaboratoryType(Map map) {
        List ids = (List)map.get("ids");
        int num = laboratoryTypeMapper.deleteLaboratoryType(ids);
        return Result.success("成功删除"+num+"条！");
    }

    @Override
    public Result getAllLaboratoryType() {
        List<LaboratoryType> laboratoryType = laboratoryTypeMapper.getAllLaboratoryType();
        return Result.success(laboratoryType);
    }
}
