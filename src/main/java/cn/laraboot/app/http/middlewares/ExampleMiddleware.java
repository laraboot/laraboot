package cn.laraboot.app.http.middlewares;

import cn.laraboot.contracts.kernel.pipeline.Stack;
import cn.laraboot.http.middlewares.HttpMiddleware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class ExampleMiddleware extends HttpMiddleware {
    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {
        // 控制器方法执行之前

        ResponseEntity response = (ResponseEntity) stack.next(request);

        // 控制器方法执行之后

        return response;
    }
}
