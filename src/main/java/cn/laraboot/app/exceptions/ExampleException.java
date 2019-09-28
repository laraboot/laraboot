package cn.laraboot.app.exceptions;

import cn.laraboot.framework.debug.RenderableException;
import org.springframework.http.HttpStatus;

public class ExampleException extends RenderableException {

    public ExampleException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
