package magnus.dust.user.repository.mapper;

import magnus.dust.user.core.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectUserById(Long id);

    User selectUserByNameAndPassword(String name, String password);

    User insertUser(User user);

    User selectUserByName(String name);
}
