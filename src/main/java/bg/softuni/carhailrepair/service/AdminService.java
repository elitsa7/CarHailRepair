package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.user.UserDTO;
import bg.softuni.carhailrepair.model.enums.UserRole;

import java.util.List;

public interface AdminService {

    List<UserDTO> listUsers();

    boolean isUserAdmin(Long userId);

    void setUserRole(Long userId, UserRole role);
}
