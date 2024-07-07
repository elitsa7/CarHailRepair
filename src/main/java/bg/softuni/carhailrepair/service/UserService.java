package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.UserLoginDTO;
import bg.softuni.carhailrepair.model.dtos.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO data);

    boolean login(UserLoginDTO userLoginDTO);
}
