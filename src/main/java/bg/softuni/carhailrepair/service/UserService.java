package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.user.UserLoginDTO;
import bg.softuni.carhailrepair.model.dtos.user.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO userLoginDTO);
}
