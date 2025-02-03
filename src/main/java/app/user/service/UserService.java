package app.user.service;

import app.user.model.dto.UserLoginRequest;
import app.user.model.dto.UserLoginResponse;
import app.user.model.dto.UserRegisterResponse;
import app.user.model.dto.UserRegisterRequest;
import jakarta.validation.Valid;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest user);

    UserLoginResponse login(UserLoginRequest user);
}
