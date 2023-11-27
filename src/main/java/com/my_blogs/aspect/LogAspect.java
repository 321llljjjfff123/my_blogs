package com.my_blogs.aspect;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常类
 */
@Aspect
@Component
@Slf4j //方便使用日志
public class LogAspect {
    //获取日志类对象
    //不用写，因为有@Slf4j
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //定义切片方法
    @Pointcut("execution(* com.example.my_blogs.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint jp) {
        //通过获取request上下文对象获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取id地址
        String id = request.getRemoteAddr();
        //获取url路径
        String url = request.getRequestURI().toString();
        //通过切面对象获取类名和方法名
        String classMethod = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
        //获取参数
        Object[] args = jp.getArgs();

        //封装到实体类对象中，打印成日志
        RequestLog logT = new RequestLog(id, url, classMethod, args);
          log.info("Request : {}", logT);
    }

    //定义后置增强方法，相当于AfterReturningAdvice，方法正常退出时执行
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        log.info("Result : {}", result);
    }

    //定义日志实体封装类
    @Data
    private class RequestLog{
        private String id;
        private String url;
        private  String classMethod;
        private Object[] args;
        public RequestLog(String id, String url, String classMethod, Object[] args) {
            this.id = id;
            this.url = url;
            this.classMethod = classMethod;
            this.args = args;
        }
    }
}
