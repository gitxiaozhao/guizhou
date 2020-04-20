package com.rainier.aop;

import com.rainier.mapper.LogMapper;
import com.rainier.model.Log;
import com.rainier.model.User;
import com.rainier.util.AddLog;
import com.rainier.util.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogMapper logMapper;
    //带有addlog注解的方法执行成功后进入aop 切面方法
    @AfterReturning(value = "execution(public * *(..)) && @annotation(addLog)",returning = "result")
    public void addLogSuccess(JoinPoint joinPoint, AddLog addLog, Object result) {
        Result re= (Result) result;
        if (re.isReturnResult().equals("200")) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            //读取session中的用户
            //请求的IP
            String ip = request.getRemoteAddr();
            Object[] parames = joinPoint.getArgs();//获取目标方法体参数
            User user=(User)session.getAttribute("user");
            if (user == null){
                return ;
            }
            Log log = new Log();
            log.setUserid(user.getId());
            //log.setTablename(Arrays.toString(parames));
            log.setValue(Arrays.toString(parames));
            log.setDate(new Timestamp(System.currentTimeMillis()));
            log.setOperation(addLog.title());
            log.setDescription(addLog.desc());
            log.setIp(ip);

            logMapper.insertSelective(log);
        }
    }

    //标注该方法体为异常通知，当目标方法出现异常时，执行该方法体
    @AfterThrowing(pointcut = "execution(public * *(..)) && @annotation(addLog)", throwing = "ex")
    public void addLog(JoinPoint joinPoint, AddLog addLog, Exception ex) {

        Log log=new Log();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        //请求的IP
        String ip = request.getRemoteAddr();
        Object[] parames = joinPoint.getArgs();//获取目标方法体参数
        User user=(User)session.getAttribute("user");
        if (user == null){
            return ;
        }
        log.setUserid(user.getId());
        log.setDate(new Timestamp(System.currentTimeMillis()));
        //log.setTablename(Arrays.toString(parames));
        log.setValue(Arrays.toString(parames));
        log.setOperation(addLog.title());
        log.setDescription("执行方法"+joinPoint.getSignature().toString()+"出错");//，异常信息+ex.toString()：
        log.setIp(ip);
        logMapper.insertSelective(log);
    }
}
