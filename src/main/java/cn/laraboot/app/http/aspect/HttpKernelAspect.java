package cn.laraboot.app.http.aspect;

import cn.laraboot.http.aspect.HttpAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HttpKernelAspect extends HttpAspect {

    @Pointcut("execution(public * cn.laraboot.app.http.controllers..*(..))")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        return this.aroundPointcut(point);
    }
}
