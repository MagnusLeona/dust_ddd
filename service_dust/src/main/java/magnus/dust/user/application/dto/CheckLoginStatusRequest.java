package magnus.dust.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckLoginStatusRequest {
    UserDto userDto;
    Cookie cookie;
}
