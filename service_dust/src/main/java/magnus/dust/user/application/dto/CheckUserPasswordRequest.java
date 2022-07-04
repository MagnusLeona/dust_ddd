package magnus.dust.user.application.dto;

import lombok.Data;

@Data
public class CheckUserPasswordRequest {
    Long userId;
    String userName;
    String password;
}
