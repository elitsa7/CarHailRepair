package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.UserRegisterDTO;

public interface UserService {
    boolean register(UserRegisterDTO data);
}
