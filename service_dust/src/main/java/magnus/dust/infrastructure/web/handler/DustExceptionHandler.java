package magnus.dust.infrastructure.web.handler;

import lombok.extern.slf4j.Slf4j;
import magnus.dust.infrastructure.service.enums.ResponseStatusEnum;
import magnus.dust.infrastructure.web.entity.DustResponse;
import magnus.dust.infrastructure.web.exception.ext.AuthCheckException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("unused")
public class DustExceptionHandler {

    @ExceptionHandler(AuthCheckException.class)
    public DustResponse loginExceptionHandler(AuthCheckException e, HttpServletRequest httpServletRequest) {
        log.error(e.getMessage());
        return DustResponse.definedError(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public DustResponse defaultExceptionHandler(Exception e) {
        log.error(e.getMessage());
        return DustResponse.definedError(ResponseStatusEnum.STATUS_UNKONWN_ERROR.code(), e.getMessage(), null);
    }
}
