package cn.laraboot.app.http;

import cn.laraboot.app.http.middlewares.ExampleGlobalMiddleware;
import cn.laraboot.app.http.middlewares.ExampleMiddleware;
import cn.laraboot.framework.auth.AuthenticateMiddleware;
import cn.laraboot.framework.contracts.http.HttpMiddlewareRegister;
import cn.laraboot.framework.contracts.http.Kernel;
import cn.laraboot.framework.contracts.kernel.pipeline.Dockable;
import cn.laraboot.framework.http.middlewares.cross.CrossMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HttpKernel implements Kernel, HttpMiddlewareRegister {

    @Autowired
    ExampleMiddleware exampleMiddleware;

    /**
     * 跨域中间件
     */
    @Autowired
    CrossMiddleware crossMiddleware;

    /**
     * 普通中间件组
     *
     * @return middleware groups
     */
    @Override
    public Map<String, List<Dockable>> registerMiddlewareGroups() {
        Map<String, List<Dockable>> groups = new HashMap<>();

        groups.put("example", Arrays.asList(exampleMiddleware));

        groups.put("jwt.auth", Arrays.asList(new AuthenticateMiddleware("jwt")));

        return groups;
    }

    /**
     * 全局中间件
     *
     * @return global middleware
     */
    @Override
    public List<Dockable> registerGlobalMiddlewares() {
        return Arrays.asList(
                new ExampleGlobalMiddleware(),
                crossMiddleware
        );
    }
}
