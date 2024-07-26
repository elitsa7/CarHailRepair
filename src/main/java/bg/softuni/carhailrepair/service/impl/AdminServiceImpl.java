package bg.softuni.carhailrepair.service.impl;

import bg.softuni.carhailrepair.model.Admin;
import bg.softuni.carhailrepair.model.User;
import bg.softuni.carhailrepair.model.dtos.user.UserDTO;
import bg.softuni.carhailrepair.model.enums.UserRole;
import bg.softuni.carhailrepair.repo.AdminRepository;
import bg.softuni.carhailrepair.repo.UserRepository;
import bg.softuni.carhailrepair.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> listUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getUserRole()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserAdmin(Long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getUserRole() == UserRole.ADMIN)
                .orElse(false);
    }

    @Override
    public void setUserRole(Long userId, UserRole role) {
        User user = userRepository.findById(userId).orElse(null);

        UserRole currentRole = user.getUserRole();
        user.setUserRole(role);
        userRepository.save(user);

        if (role == UserRole.ADMIN && currentRole != UserRole.ADMIN) {
            createAdminIfNotExists(user);
        } else if (role != UserRole.ADMIN && currentRole == UserRole.ADMIN) {
            deleteAdminIfExists(user);
        }
    }

    private void createAdminIfNotExists(User user) {
        if(adminRepository.findByUserId(user.getId()).isEmpty()) {
            Admin admin = new Admin();
            admin.setUser(user);
            adminRepository.save(admin);
        }
    }

    private void deleteAdminIfExists(User user) {
        Optional<Admin> optionalAdmin = adminRepository.findById(user.getId());
        optionalAdmin.ifPresent(adminRepository::delete);
    }
}
