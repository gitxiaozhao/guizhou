package com.rainier.service.impl;

import com.rainier.mapper.LaboratoryMapper;
import com.rainier.model.Laboratory;
import com.rainier.model.User;
import com.rainier.service.LaboratoryService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {
    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Override
    public Result addLaboratory(Laboratory laboratory) {
        /*有id修改无id新增*/
        if (laboratory.getId() != null){
            int count = laboratoryMapper.updateLaboratory(laboratory);
            if (count>0){
                return Result.success("修改成功！");
            }else {
                return Result.success("修改失败！");
            }

        }else {
            int count = laboratoryMapper.addLaboratory(laboratory);
            if (count>0){
                return Result.success("添加成功！");
            }else {
                return Result.success("添加失败！");
            }
        }


    }

    @Override
    public Result getLaboratoryList(Map map) {

        String key = map.get("key").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page page = new Page();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(laboratoryMapper.queryLaboratoryCount(key));
        page.setList(laboratoryMapper.queryLaboratoryList(key, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result getLaboratoryById(Laboratory laboratory) {
        Map lab = laboratoryMapper.getLaboratoryById(laboratory);
        return Result.success(lab);
    }

    @Override
    public Result deleteLaboratoryByIds(Map map) {
        List ids = (List) map.get("ids");
        int num = laboratoryMapper.deleteLaboratoryByIds(ids);
        if (num > 0){
            return Result.success("成功删除"+num+"条！");
        }else {
            return Result.error("删除失败！");
        }

    }

    @Override
    public Result getLaboratoryListByRecommend1(Map map) {
        String ltid = map.get("ltid").toString();
        List<Map> list = laboratoryMapper.getLaboratoryListByRecommend1(ltid);
        return Result.success(list);
    }
}
