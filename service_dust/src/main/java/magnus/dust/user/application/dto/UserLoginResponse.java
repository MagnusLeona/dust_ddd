package magnus.dust.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    // 需要返回Cookie，和用户信息
    UserDto userDto;
    Cookie cookie;
}
