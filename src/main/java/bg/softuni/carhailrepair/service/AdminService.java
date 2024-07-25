package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.user.UserDTO;

import java.util.List;

public interface AdminService {

    List<UserDTO> listUsers();
}
