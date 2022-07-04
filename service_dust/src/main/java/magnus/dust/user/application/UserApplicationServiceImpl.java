package magnus.dust.user.application;

import magnus.dust.application.ApplicationServiceBase;
import magnus.dust.user.application.dto.*;
import magnus.dust.user.core.InvalidUserException;
import magnus.dust.user.core.User;
import magnus.dust.user.core.UserRepository;

import java.security.InvalidParameterException;

public class UserApplicationServiceImpl extends ApplicationServiceBase implements UserApplicationService {

    UserRepository userRepository;

    @Override
    public GetUserInfoDetailResponse getUserInfo(GetUserInfoDetailRequest request) throws InvalidUserException {
        if (request == null || request.getUserId() == null) {
            throw new InvalidUserException();
        }
        User user = userRepository.getById(request.getUserId());
        if (user == null) {
            throw new InvalidUserException();
        }
        UserDto userDto = mapUserToDto(user);
        //  �����û���Ϣ
        // ������
        // 1. ���ڴ˿ͻ���
        // 2.�����ڴ˿ͻ���
        return new GetUserInfoDetailResponse(userDto);
    }

    @Override
    public CheckUserPasswordResponse checkUserPassword(CheckUserPasswordRequest request) {
        if (request == null || request.getUserName() == null || request.getPassword() == null) {
            return new CheckUserPasswordResponse(false);
        }

        Boolean isMatch = userRepository.passwordMatch(request.getUserName(), request.getPassword());
        return new CheckUserPasswordResponse(isMatch);
    }

    @Override
    public UserLoginResponse userLogin(UserLoginRequest request) throws InvalidUserException {
        // ��¼��
        //
        if (request == null || (request.getUserDto() == null && request.getCookie() == null)) {
            throw new InvalidParameterException();
        }
        // ����cookie�Ĳ����ԡ�
        User user = userRepository.userLogin(mapDtoToUser(request.getUserDto()), request.getCookie());
        if (user == null) {
            throw new InvalidUserException();
        }
        // user ��ʲô�ģ�
        UserDto userDto = mapUserToDto(user);
        return new UserLoginResponse(userDto, request.getCookie());
    }

    @Override
    public CheckLoginStatusResponse checkLoginStatus(CheckLoginStatusRequest request) {
        return null;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        return null;
    }

    private UserDto mapUserToDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setDescription(user.getDescription());
        return userDto;
    }

    private User mapDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDescription(userDto.getDescription());
        return user;
    }

    public UserApplicationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
