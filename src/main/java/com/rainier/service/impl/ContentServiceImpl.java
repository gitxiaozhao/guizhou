package com.rainier.service.impl;

import com.rainier.mapper.ContentMapper;
import com.rainier.model.Content;
import com.rainier.model.User;
import com.rainier.service.ContentService;
import com.rainier.util.Page;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public Result addContent(Map map) {
        //创建对象
        Content content = new Content();

        if (!map.get("id").toString().equals("") && map.get("id").toString() != null){
            content.setId(Integer.parseInt(map.get("id").toString()));
        }else {
            content.setId(null);
        }
        //把数据放入对象
        content.setTitle(map.get("title").toString());
        content.setLabelId(Integer.parseInt(map.get("labelId").toString()));
        content.setContent(map.get("content").toString());
        List<String> urlList = (List<String>) map.get("imgUrl");
        String url = "";
        for (String u : urlList){
            if (url.equals("")){
                url += u;
            }else {
                url += ","+u;
            }

        }
        content.setImgUrl(url);

        if (content.getId() == null){
            //新增
            contentMapper.addContent(content);
            return Result.success("新增成功！");
        }else {
            //修改
            contentMapper.updateContent(content);
            return Result.success("修改成功！");
        }


    }

    @Override
    public Result getContentList(Map map) {
        String title = map.get("title").toString();
        String labelId = map.get("labelId").toString();

        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());//当前页数
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());//每页条数
        Page<Content> page = new Page<Content>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        page.setTotalRecords(contentMapper.getContentListCount(title,labelId));
        page.setList(contentMapper.getContentList(title,labelId, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize()));
        return Result.success(page);
    }

    @Override
    public Result deleteContent(Map map) {
        List contentids = (List) map.get("contentid");
        if (contentids != null && contentids.size() > 0){
            //删除用户
            contentMapper.deleteLabel(contentids);
            return Result.success("删除成功");
        }else {
            return Result.error("用户id为空");
        }
    }

    @Override
    public Result getContentById(Map map) {
        Content content = contentMapper.getContentById(Integer.parseInt(map.get("contentid").toString()));
        return Result.success(content);
    }
}
