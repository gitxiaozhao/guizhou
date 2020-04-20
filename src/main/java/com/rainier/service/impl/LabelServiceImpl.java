package com.rainier.service.impl;

import com.rainier.mapper.LabelMapper;
import com.rainier.model.Label;
import com.rainier.model.User;
import com.rainier.service.LabelService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelMapper labelMapper;

    @Override
    public Result addLabel(Map map) {
        Label label = new Label();

        if (map.get("id").toString().equals("") || map.get("id") == null){
            label.setId(null);
        }else {
            label.setId(Integer.parseInt(map.get("id").toString()));
        }
        label.setName(map.get("name").toString());


        //有id修改无id添加
        if (label.getId() == null){
            //添加前先做验证是否重复
            Label lab = labelMapper.getLabelByName(label);
            if (lab == null){
                labelMapper.addLabel(label);
                return Result.success("添加成功");
            }else{
                return Result.error("此标签已存在");
            }

        }else{
            labelMapper.updateLabel(label);
            return Result.success("修改成功");
        }
    }

    @Override
    public Result getLabel(Map map) {
        String name = map.get("name").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<Label> page = new Page<Label>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(labelMapper.getLabelCount(name));
        page.setList(labelMapper.getLabelList(name, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result deleteLabel(Map map) {
        List labelids = (List) map.get("labelids");
        if (labelids != null && labelids.size() > 0){
            //先删除指定的标签
            labelMapper.deleteLabel(labelids);

            return Result.success("删除成功");
        }else {
            return Result.error("用户id为空");
        }
    }

    @Override
    public Result getAllLabel() {
        List<Label> list = labelMapper.getAllLabel();
        return Result.success(list);
    }

    @Override
    public Result getLabelById(Map map) {
        Label label = labelMapper.getLabelById(Integer.parseInt(map.get("labelid").toString()));
        return Result.success(label);
    }
}
