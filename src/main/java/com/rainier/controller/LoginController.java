package com.rainier.controller;

import com.rainier.model.Menu;
import com.rainier.service.LoginService;
import com.rainier.model.User;
import com.rainier.util.AddLog;
import com.rainier.util.MD5Util;
import com.rainier.util.Result;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
* @描述 登录注销
* @参数注释：
* @创建人  wyz
* @创建时间  2019/11/26
*/
@Controller("loginController")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
    * @描述 用户登录
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/12/2
    */
    @AddLog(title = "用户登录")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result userLogin(String name, String password, HttpServletRequest request){
        User user = (User) loginService.getUserByLogin(name, password).getData();
        if(user==null){
            return Result.error("账号或密码不正确！");
        }else{

            loginService.updateLoginTime(user.getId());
            request.getSession().setAttribute("user", user);
            return Result.success(user);

        }
    }

    /**
     * 用户退出的方法
     */
    @RequestMapping(value = "/logOut",method = RequestMethod.POST)
    @ResponseBody
    public Result quit(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return new Result("200","success");
    }

    /**
    * @描述 获取菜单
    * @参数注释：
    * @创建人  wyz
    * @创建时间  2019/11/29
    */
    @RequestMapping(value="/getModulesAll",method = RequestMethod.GET)
    @ResponseBody
    public Result getModulesInfoAlls(HttpServletRequest request){
        //查询所有权限
        List<Menu> modules=loginService.getModuleByLevelType();
        //获取用户对象
        User userInfo =(User) request.getSession().getAttribute("user");
        if(userInfo == null){
            return new Result("500","用户没有登录");
        }else {
            //根据用户id查询用户拥有的权限
            List<Menu> permissions = loginService.selModulesAll(userInfo.getId());

            return new Result("200","success",getModuleList(permissions,modules));
        }
    }

    private Map<String,Object> getModuleList(List<Menu> permissions, List<Menu> modules){
        Map<String,Object> map=new HashedMap();

        //循环所有
        for (int i=0;i<modules.size();i++){
            //循环用户
            for (Menu m: permissions){
                if(m.getId().equals(modules.get(i).getId())){
                    map.put(modules.get(i).getUrl(),true);
                    break;
                }else{
                    map.put(modules.get(i).getUrl(),false);
                }
            }
        }

        /*modules.forEach(module -> {
            for (Modules m: permissions){
                if(m.getId().equals(module.getId())){
                    map.put(module.getUrl(),true);
                    break;
                }else{
                    map.put(module.getUrl(),false);
                }
            }
        });*/
        return map;
    }
}
