package magnus.dust.user.repository;

import com.alibaba.fastjson.JSON;
import magnus.dust.infrastructure.repos.redis.RedisService;
import magnus.dust.infrastructure.service.constant.SessionAttributeConstant;
import magnus.dust.user.core.User;
import magnus.dust.user.core.UserRepository;
import magnus.dust.user.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    @Override
    public User getById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public Boolean passwordMatch(String userName, String password) {
        User user = userMapper.selectUserByNameAndPassword(userName, password);
        return user != null;
    }

    @Override
    public User userLogin(User user, Cookie cookie) {
        if (!Objects.isNull(cookie)) {
            //
            if (redisService.exist(cookie.getValue())) {
                // 如果存在，则说明已经登录
                // 刷新登录状态
                redisService.flushKey(cookie.getValue(), SessionAttributeConstant.SESSION_MAX_INACTIVE_TIME);
                String s = redisService.get(cookie.getValue());
                return JSON.parseObject(s, User.class);
            }
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        return userMapper.insertUser(user);
    }
}
