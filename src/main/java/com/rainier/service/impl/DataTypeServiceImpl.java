package com.rainier.service.impl;

import com.rainier.mapper.DataTypeMapper;
import com.rainier.model.DataType;
import com.rainier.model.Label;
import com.rainier.service.DataTypeService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DataTypeServiceImpl implements DataTypeService {

    @Autowired
    private DataTypeMapper dataTypeMapper;

    @Override
    public Result addOrUpdateDateType(Map map) {
        DataType dataType = new DataType();

        if (map.get("id").toString().equals("") || map.get("id") == null){
            dataType.setId(null);
        }else {
            dataType.setId(Integer.parseInt(map.get("id").toString()));
        }
        dataType.setName(map.get("name").toString());
        dataType.setNewDate(new Date());

        //有id修改无id添加
        if (dataType.getId() == null){
            //添加前先做验证是否重复
            DataType d = dataTypeMapper.getDataTypeByName(dataType);
            if (d == null){
                dataTypeMapper.addDateType(dataType);
                return Result.success("添加成功");
            }else{
                return Result.error("此数据类型已存在");
            }
        }else{
            dataTypeMapper.updateDateType(dataType);
            return Result.success("修改成功");
        }

    }

    @Override
    public Result getDataType(Map map) {
        String name = map.get("name").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<DataType> page = new Page<DataType>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(dataTypeMapper.getDataTypeCount(name));
        page.setList(dataTypeMapper.getDataTypeList(name, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result deleteDataType(Map map) {
        List dataTypeids = (List) map.get("dataTypeids");
        if (dataTypeids != null && dataTypeids.size() > 0){
            //先删除指定的数据类型
            dataTypeMapper.deleteDataType(dataTypeids);

            return Result.success("删除成功");
        }else {
            return Result.error("用户id为空");
        }
    }

    @Override
    public Result getDataTypeById(Map map) {
        DataType dt = dataTypeMapper.getDataTypeById(Integer.parseInt(map.get("dataTypeId").toString()));
        return Result.success(dt);
    }
}
