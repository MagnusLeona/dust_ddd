package magnus.dust.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    // ��Ҫ����Cookie�����û���Ϣ
    UserDto userDto;
    Cookie cookie;
}
