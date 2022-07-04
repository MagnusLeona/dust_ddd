package magnus.dust.infrastructure.web.exception.ext;

import magnus.dust.infrastructure.service.enums.ExceptionCodeEnum;
import magnus.dust.infrastructure.web.exception.DustException;

public class UserArticleException extends DustException {
    public UserArticleException(Integer code, String msg) {
        super(code, msg);
    }

    public static UserArticleException nullParameterException() {
        return new UserArticleException(ExceptionCodeEnum.ARGUMENT_RESOLVER_EXCEPTION.getCode(), "User or Article cannot be null!");
    }
}
