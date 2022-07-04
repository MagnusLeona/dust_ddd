package magnus.dust.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    //  �û���Ϣdto
    Long id;
    String name;
    String description;
    String email;
    String password;
}
