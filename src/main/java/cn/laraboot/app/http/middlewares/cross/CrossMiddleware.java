package cn.laraboot.app.http.middlewares.cross;

import cn.laraboot.contracts.kernel.pipeline.Stack;
import cn.laraboot.http.middlewares.HttpMiddleware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class CrossMiddleware extends HttpMiddleware {

    private Crosses crosses;

    public CrossMiddleware(CrossProvider provider) {
        this.crosses = provider.get();
    }

    @Override
    public ResponseEntity handle(HttpServletRequest request, Stack stack) throws Throwable {
        Cross cross = crosses.get(request.getRemoteHost());
        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        if (cross != null && Arrays.asList(cross.getMethods()).contains(method)) { // 判断是否允许跨域
            ResponseEntity response = (ResponseEntity) stack.next(request);
            List<String> withHeaders = Arrays.asList(cross.getWithHeaders());
            HttpHeaders headers = response.getHeaders();
            headers.setAccessControlAllowCredentials(cross.isWithCredentials());
            headers.setAccessControlAllowHeaders(withHeaders);
            headers.setAccessControlAllowOrigin(cross.getHost());
            headers.setAccessControlAllowMethods(Arrays.asList(cross.getMethods()));
            headers.setAccessControlExposeHeaders(withHeaders);
            return response;
        }

        return (ResponseEntity) stack.next(request);
    }
}
