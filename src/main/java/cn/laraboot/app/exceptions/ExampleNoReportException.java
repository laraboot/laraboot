package cn.laraboot.app.exceptions;

import cn.laraboot.framework.debug.RenderableException;
import org.springframework.http.HttpStatus;

public class ExampleNoReportException extends RenderableException {

    public ExampleNoReportException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
