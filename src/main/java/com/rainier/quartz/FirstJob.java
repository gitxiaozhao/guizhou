package com.rainier.quartz;


import com.rainier.service.ContentService;
import com.rainier.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@EnableScheduling
@Component
public class FirstJob {

    @Autowired
    private UrlService urlService;

    public void task() {
        System.out.println("任务1执行....");
        urlService.quartzDeleteImgUrl();
    }
    public void task2(){
        System.out.println("任务2执行....");
        urlService.deleteFile();
    }

}
