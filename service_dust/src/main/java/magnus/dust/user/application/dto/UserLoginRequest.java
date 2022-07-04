package magnus.dust.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    // 需要用户账户
    // 需要cookie去判断当前是否已经登录
    UserDto userDto;
    Cookie cookie;
}
