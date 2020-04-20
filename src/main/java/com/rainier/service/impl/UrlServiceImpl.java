package com.rainier.service.impl;

import com.rainier.mapper.ContentMapper;
import com.rainier.mapper.LaboratoryMapper;
import com.rainier.mapper.UrlDao;
import com.rainier.model.Url;
import com.rainier.service.UrlService;
import com.rainier.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class UrlServiceImpl implements UrlService {

    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    @Autowired
    private UrlDao urlDao;


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    //插入
    public int insertUrl(String name,String lujing,String url){
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=lujing=="+lujing+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int jieguo=urlDao.insertUrl(name,lujing,url);
        System.out.print("插入结果==="+jieguo+"\n");
        return jieguo;
    }
    //查询
    public List<Url> selectShipin() {
        List<Url> urls = urlDao.selectShipin();
        return urls;

    }

    @Override
    public void quartzDeleteImgUrl() {
        /*存放所有url的集合*/
        List<String> urlList = new ArrayList();

        /*发布内容*/
        List<Map> contentImgList = contentMapper.getAllUrl();
        List<Map> contentImgLists = new ArrayList<>();
        for (Map laboratoryMap : contentImgList){
            String[] imgs = laboratoryMap.get("imgUrl").toString().split(",");
            for (int i=0;i< imgs.length;i++){
                Map map = new HashMap();
                map.put("imgUrl",imgs[i]);
                contentImgLists.add(map);
            }
        }

        /*实验室*/
        List<Map> laboratoryImgList = laboratoryMapper.getAllUrl();
        List<Map> laboratoryImgLists = new ArrayList<>();
        for (Map laboratoryMap : laboratoryImgList){
            String[] imgs = laboratoryMap.get("img").toString().split(",");
            for (int i=0;i< imgs.length;i++){
                Map map = new HashMap();
                map.put("imgUrl",imgs[i]);
                laboratoryImgLists.add(map);
            }
        }

        List<List<Map>> list = new ArrayList<>();
        list.add(contentImgLists);
        list.add(laboratoryImgLists);

        for (List<Map> a : list){
            for (Map m : a){
                if (m.get("imgUrl")!=null){
                    urlList.add(m.get("imgUrl").toString());
                }

            }
        }
        //删除多余数据
        urlDao.deleteImgurlByUrl(urlList);


    }

    @Override
    public void deleteFile() {
        //查询所有绝对路径
        List<Map> urls = urlDao.getImgurlLujing();
        Scanner sc = new Scanner(System.in);
        String pan = "D:/fileUpload/";
        File file = new File(pan);
        File[] files = file.listFiles();
        lists(file,urls);
    }


    public static void lists(File file,List<Map> urls) {
        if (file.isDirectory()) { // 判断是否为文件夹
            File[] list = file.listFiles(); // 使用数组接收带有完整路径的文件夹
            if (list != null) {

                // 循环遍历文件
                for (int i = 0; i < list.length; i++) {
                    Integer ta = 0;
                    //遍历路径
                    for (int j = 0; j < urls.size(); j++) {
                        if (list[i].toString().contains(urls.get(j).get("name").toString())){
                            ta = 1;
                        }
                    }
                    //不等于1证明没有匹配到
                    if (ta != 1){
                        File file1 = new File(list[i].toString());
                        file1.delete();
                    }
                }
            }
        }
    }

    @Override
    public Result deleteFile(Map map) {
        String url = map.get("url").toString();
        //获得绝对路径
        List<Map> urls = urlDao.getImgUrlByUrl(url);
        String lujing = urls.get(0).get("lujing").toString();

        //创建文件对象
        File file = new File(lujing);
        //判断文件是否存在
        if (file.exists()) {
            //删除文件
            file.delete();

            urlDao.deleteImgUrlById(Integer.parseInt(urls.get(0).get("id").toString()));
            return Result.success("文件已经被成功删除");
        }else {
            return Result.error("文件不存在");
        }


    }

}
