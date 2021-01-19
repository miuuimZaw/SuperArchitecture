package com.zaw.superarch.aop;

import com.zaw.superarch.anno.LogOut;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TBox升级服务日志记录
 *
 * @author zhangaiwen
 */
@Aspect
@Component
public class UpgradeLogAop {

    /**
     * 切入点
     */
    @Pointcut(value = "@annotation(com.zaw.superarch.anno.LogOut)")
    public void aspectjPoint() {
        // Do nothing
    }

    /**
     * 异常通知
     */
    @AfterThrowing(pointcut = "aspectjPoint()", throwing = "e")
    public void fail(JoinPoint p, Throwable e) {
        // TODO 异常时执行
        getBasicInfo(p);
    }

    /**
     * 后置通知
     */
    @AfterReturning("aspectjPoint()")
    public void success(JoinPoint p) {
        // TODO 没有异常正确执行完成执行
        getBasicInfo(p);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void saveLog() {
        // TODO 数据落地
    }

    /**
     * 获取切面的基础信息
     *
     * @param p
     */
    private void getBasicInfo(JoinPoint p) {
        // 方法名
        String methodName = p.getSignature().getName();
        // 注解详情
        Method method = ((MethodSignature) p.getSignature()).getMethod();
        LogOut logAnno = method.getAnnotation(LogOut.class);
        String desc = "";
        if (Objects.nonNull(logAnno)) {
            desc = logAnno.description();
        }
    }

    /**
     * 获取IP地址
     *
     * @return IP
     */
    private String getIp() {
        HttpServletRequest request = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        if (Objects.isNull(request)) {
            return "";
        }
        String ip = "";
        // X-Forwarded-For:Squid 服务代理
        String ipAddr = request.getHeader("X-Forwarded-For");
        String unknown = "unknown";
        if (StringUtils.isBlank(ipAddr) || StringUtils.equals(unknown, ipAddr)) {
            // Proxy-Client-IP:apache 服务代理
            ipAddr = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddr) || StringUtils.equals(unknown, ipAddr)) {
            // WL-Proxy-Client-IP:weblogic 服务代理
            ipAddr = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ipAddr) || StringUtils.equals(unknown, ipAddr)) {
            //HTTP_CLIENT_IP:有些代理服务器
            ipAddr = request.getHeader("HTTP_CLIENT_IP");
        }

        if (StringUtils.isBlank(ipAddr) || StringUtils.equals(unknown, ipAddr)) {
            //X-Real-IP:nginx服务代理
            ipAddr = request.getHeader("X-Real-IP");
        }

        // 有些网络通过多层代理,那么获取到的ip就会有多个,一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (StringUtils.isNotBlank(ipAddr)) {
            ip = ipAddr.split(",")[0];
        }

        // 还是不能获取到,最后再通过request.getRemoteAddr();获取
        if (StringUtils.isBlank(ipAddr) || StringUtils.equalsIgnoreCase(unknown, ipAddr)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 校验是否包含中文字符
     *
     * @param msg 异常信息
     * @return 是否业务异常
     */
    private Boolean validBizException(String msg) {
        String regex = "[\\u4e00-\\u9fa5]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(msg);
        return m.find();
    }
}
