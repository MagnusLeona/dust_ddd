package magnus.dust.user.application;

import magnus.dust.application.ApplicationService;
import magnus.dust.user.application.dto.*;
import magnus.dust.user.core.InvalidUserException;

public interface UserApplicationService extends ApplicationService {
    // ”√ªß
    GetUserInfoDetailResponse getUserInfo(GetUserInfoDetailRequest request) throws InvalidUserException;

    CheckUserPasswordResponse checkUserPassword(CheckUserPasswordRequest request);

    UserLoginResponse userLogin(UserLoginRequest request) throws InvalidUserException;

    CheckLoginStatusResponse checkLoginStatus(CheckLoginStatusRequest request);

    CreateUserResponse createUser(CreateUserRequest request);
}
