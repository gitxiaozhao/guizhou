package com.rainier.service.impl;

import com.rainier.mapper.DataClassifyMapper;
import com.rainier.model.DataClassify;
import com.rainier.model.DataType;
import com.rainier.service.DataClassifyService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DataClassifyServiceImpl implements DataClassifyService {

    @Autowired
    private DataClassifyMapper dataClassifyMapper;

    @Override
    public Result addOrUpdateDataClassify(Map map) {
        DataClassify dataClassify = new DataClassify();

        if (map.get("id").toString().equals("") || map.get("id") == null){
            dataClassify.setId(null);
        }else {
            dataClassify.setId(Integer.parseInt(map.get("id").toString()));
        }
        dataClassify.setName(map.get("name").toString());
        dataClassify.setNewDate(new Date());

        //有id修改无id添加
        if (dataClassify.getId() == null){
            //添加前先做验证是否重复
            DataType d = dataClassifyMapper.getDataClassifyByName(dataClassify);
            if (d == null){
                dataClassifyMapper.addDataClassify(dataClassify);
                return Result.success("添加成功");
            }else{
                return Result.error("此数据分类已存在");
            }
        }else{
            dataClassifyMapper.updateDataClassify(dataClassify);
            return Result.success("修改成功");
        }
    }

    @Override
    public Result getDataClassify(Map map) {
        String name = map.get("name").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<DataClassify> page = new Page<DataClassify>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(dataClassifyMapper.getDataClassifyCount(name));
        page.setList(dataClassifyMapper.getDataClassifyList(name, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result deleteDataClassify(Map map) {
        List dataClassifyids = (List) map.get("dataClassifyids");
        if (dataClassifyids != null && dataClassifyids.size() > 0){
            //先删除指定的数据类型
            dataClassifyMapper.deleteDataClassify(dataClassifyids);

            return Result.success("删除成功");
        }else {
            return Result.error("用户id为空");
        }
    }

    @Override
    public Result getDataClassifyById(Map map) {
        DataClassify dc = dataClassifyMapper.getDataClassifyById(Integer.parseInt(map.get("dataClassifyId").toString()));
        return Result.success(dc);
    }
}
