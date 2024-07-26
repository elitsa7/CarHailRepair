package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.user.UserLoginDTO;
import bg.softuni.carhailrepair.model.dtos.user.UserRegisterDTO;
import bg.softuni.carhailrepair.model.enums.UserRole;

public interface UserService {
    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}
