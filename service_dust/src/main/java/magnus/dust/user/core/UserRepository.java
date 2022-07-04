package magnus.dust.user.core;

import magnus.dust.core.Repository;
import magnus.dust.user.core.User;

import javax.servlet.http.Cookie;

public interface UserRepository extends Repository<User, Long> {

    @Override
    User getById(Long id);

    Boolean passwordMatch(String userName, String password);

    User userLogin(User user, Cookie cookie);

    User createUser(User user);
}
