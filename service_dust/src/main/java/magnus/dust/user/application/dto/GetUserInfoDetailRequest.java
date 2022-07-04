package magnus.dust.user.application.dto;

import lombok.Data;

@Data
public class GetUserInfoDetailRequest {
    // 根据客户号查询用户Request
    Long userId;
}
