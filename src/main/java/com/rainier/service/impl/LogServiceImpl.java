package com.rainier.service.impl;

import com.rainier.model.Log;
import com.rainier.util.Page;
import com.rainier.util.StringUtil;
import com.rainier.mapper.LogMapper;
import com.rainier.service.LogService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Crystal on 2018/7/11.
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    public LogMapper getLogMapper() {
        return logMapper;
    }

    public void setLogMapper(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    private static final String TIME_SUFFIX = " 00:00:00";

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Result selByPage(Map map) throws ParseException {

        String username = map.get("username").toString();

        String starttime = map.get("starttime").toString();
        String endtime = map.get("endtime").toString();
        Integer pageIndex = Integer.parseInt(map.get("pageIndex").toString());
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());


        Timestamp start;
        Timestamp end;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*if (StringUtil.equalsIsNullString(userid)) {
            userid = userid;
        }else{
            userid = null;
        }*/
        /*if (StringUtil.equalsIsNullString(tablename)) {
            tablename = "%" + tablename +"%";
        }else{
            tablename = null;
        }*/
        Date d= null;
        if (StringUtil.equalsIsNullString(starttime)) {
            starttime+=TIME_SUFFIX;
            try {
                d = sdf.parse(starttime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            start = new Timestamp(d.getTime());
        }else{
            start = null;
        }

        if (StringUtil.equalsIsNullString(endtime)) {
            endtime+=TIME_SUFFIX;
            try {
                d = sdf.parse(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            end = new Timestamp(d.getTime());
        }
        else{
            end = null;
        }

        Page<Log> page = new Page<Log>();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        Integer integer = logMapper.selectNumBer(username, start, end);
        page.setTotalRecords(integer);
        List<Log> logs = logMapper.selectByPage(username, start, end, ((page.getPageIndex() - 1) * page.getPageSize()), page.getPageSize());

        page.setList(logs);
        return Result.success(page);
    }
}
