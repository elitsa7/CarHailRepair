package bg.softuni.carhailrepair.service.impl;

import bg.softuni.carhailrepair.model.User;
import bg.softuni.carhailrepair.model.dtos.user.UserDTO;
import bg.softuni.carhailrepair.repo.AdminRepository;
import bg.softuni.carhailrepair.repo.UserRepository;
import bg.softuni.carhailrepair.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
