package magnus.dust.user.application;

import magnus.dust.infrastructure.service.dict.CookieNameDict;
import magnus.dust.user.application.dto.*;
import magnus.dust.user.core.InvalidUserException;
import magnus.dust.user.core.User;
import magnus.dust.user.core.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Cookie;

class UserApplicationServiceImplTest {

    UserRepository userRepository = Mockito.mock(UserRepository.class);
    UserApplicationService userApplicationService = new UserApplicationServiceImpl(userRepository);

    @Test
    void getUserInfo() throws InvalidUserException {
        User user = new User();
        user.setId(1L);
        Mockito.when(userRepository.getById(1L)).thenReturn(user);
        GetUserInfoDetailRequest getUserInfoDetailRequest = new GetUserInfoDetailRequest();
        getUserInfoDetailRequest.setUserId(1L);
        GetUserInfoDetailResponse userInfo = userApplicationService.getUserInfo(getUserInfoDetailRequest);
        assert userInfo != null;
        assert userInfo.getUserDto() != null;
        assert userInfo.getUserDto().getId() != null;
        assert userInfo.getUserDto().getId() == 1L;

        Mockito.when(userRepository.getById(2L)).thenReturn(null);
    }

    @Test
    void checkPassword() {
        User user = new User();
        user.setId(1L);
        Mockito.when(userRepository.passwordMatch("magnus", "magnus")).thenReturn(true);

        CheckUserPasswordRequest checkUserPasswordRequest = new CheckUserPasswordRequest();
        checkUserPasswordRequest.setPassword("magnus");
        checkUserPasswordRequest.setUserName("magnus");


        CheckUserPasswordResponse checkUserPasswordResponse = userApplicationService.checkUserPassword(checkUserPasswordRequest);
        assert checkUserPasswordResponse != null;
        assert checkUserPasswordResponse.isMatch();
    }

    @Test
    void userLogin() throws InvalidUserException {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setCookie(new Cookie(CookieNameDict.COOKIE_LOGIN_TOKEN, "12"));
        User user = new User();
        Mockito.when(userRepository.userLogin(null, userLoginRequest.getCookie())).thenReturn(user);

        UserLoginResponse userLoginResponse = userApplicationService.userLogin(userLoginRequest);

        assert userLoginResponse != null;

    }

    @Test
    void createUser() {
        // 创建用户
        UserDto userDto = new UserDto();
        userDto.setName("test name");
        userDto.setEmail("abc@abc.com");
        userDto.setDescription("description test");
        userDto.setPassword("password");
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserDto(userDto);
        CreateUserResponse user = userApplicationService.createUser(createUserRequest);
        boolean successful = user.isSuccessful();
        assert successful;
    }
}