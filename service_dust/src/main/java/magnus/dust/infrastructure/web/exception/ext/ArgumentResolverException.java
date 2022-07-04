package magnus.dust.infrastructure.web.exception.ext;

import magnus.dust.infrastructure.service.dict.ExceptionMessageDict;
import magnus.dust.infrastructure.service.enums.ExceptionCodeEnum;
import magnus.dust.infrastructure.web.exception.DustException;

public class ArgumentResolverException extends DustException {

    public ArgumentResolverException(Integer code, String s) {
        super(code, s);
    }

    public static DustException defaultArgsResolverException() {
        return new ArgumentResolverException(ExceptionCodeEnum.ARGUMENT_RESOLVER_EXCEPTION.getCode(), ExceptionMessageDict.ARGUMENT_RESOLVER_ERROR);
    }
}
