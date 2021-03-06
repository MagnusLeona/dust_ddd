package magnus.dust.infrastructure.service.enums;

public enum ExceptionCodeEnum {

    ARGUMENT_RESOLVER_EXCEPTION(0),
    AUTHCHECK_EXCEPTION(1),
    ARTICLE_CREATE_EXCEPTION(2);

    int code;

    ExceptionCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
