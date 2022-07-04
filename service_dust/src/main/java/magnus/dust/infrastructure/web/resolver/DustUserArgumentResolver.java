package magnus.dust.infrastructure.web.resolver;

import magnus.dust.infrastructure.repos.redis.RedisService;
import magnus.dust.infrastructure.service.dict.CookieNameDict;
import magnus.dust.infrastructure.service.utils.WebUtils;
import magnus.dust.infrastructure.web.exception.ext.AuthCheckException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class DustUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    RedisService redisService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(InjectUser.class) && parameter.getParameterType().isAssignableFrom(User.class);
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 从Session中解析
        HttpServletRequest nativeRequest = (HttpServletRequest) webRequest.getNativeRequest();
        Cookie cookieByName = WebUtils.getCookieByName(CookieNameDict.COOKIE_LOGIN_TOKEN, nativeRequest);
        if (Objects.isNull(cookieByName) || !redisService.exist(cookieByName.getValue())) {
            throw AuthCheckException.defaultAuthCheckException();
        }
//        return JSON.parseObject(redisService.get(cookieByName.getValue()), User.class);
        return null;
    }
}
