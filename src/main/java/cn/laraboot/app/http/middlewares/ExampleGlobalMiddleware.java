package cn.laraboot.app.http.middlewares;

import cn.laraboot.contracts.kernel.pipeline.Stack;
import cn.laraboot.http.middlewares.HttpMiddleware;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class ExampleGlobalMiddleware extends HttpMiddleware {
    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {
        return (ResponseEntity) stack.next(request);
    }
}
