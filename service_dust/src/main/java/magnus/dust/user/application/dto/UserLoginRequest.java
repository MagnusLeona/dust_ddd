package magnus.dust.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    // ��Ҫ�û��˻�
    // ��Ҫcookieȥ�жϵ�ǰ�Ƿ��Ѿ���¼
    UserDto userDto;
    Cookie cookie;
}
