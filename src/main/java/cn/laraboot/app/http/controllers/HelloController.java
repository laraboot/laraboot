package cn.laraboot.app.http.controllers;

import cn.laraboot.app.events.ExampleEvent;
import cn.laraboot.app.exceptions.ExampleException;
import cn.laraboot.app.models.User;
import cn.laraboot.framework.auth.AuthManager;
import cn.laraboot.framework.http.controller.BaseController;
import cn.laraboot.framework.http.middlewares.Middleware;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CacheConfig(cacheNames = "hello")
@Middleware(groups = "example")
public class HelloController extends BaseController {

    @Autowired
    AuthManager authManager;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping("/")
    public Object hello(RequestFacade request) {
        User user = (User) authManager.user();
//        User user = (User) authManager.user("guard");
        return ok(user != null ? user.getName() + "用户已经登录" : "用户未登录");
    }

    @RequestMapping("/auth")
    @Middleware(groups = "jwt.auth")
    public Object auth(HttpServletRequest request) {
        return ok(authManager.user());
    }

    @RequestMapping("/event")
    public Object sendEvent() {
        eventPublisher.publishEvent(new ExampleEvent(this, "测试"));
        return raw("响应");
    }

    @RequestMapping("/exception")
    public Object testException() throws Throwable {
        throw new ExampleException("testException");
    }
}
