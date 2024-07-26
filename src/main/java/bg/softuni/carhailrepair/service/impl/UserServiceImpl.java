package bg.softuni.carhailrepair.service.impl;

import bg.softuni.carhailrepair.model.Admin;
import bg.softuni.carhailrepair.model.User;
import bg.softuni.carhailrepair.model.dtos.user.UserLoginDTO;
import bg.softuni.carhailrepair.model.dtos.user.UserRegisterDTO;
import bg.softuni.carhailrepair.model.enums.UserRole;
import bg.softuni.carhailrepair.repo.AdminRepository;
import bg.softuni.carhailrepair.repo.UserRepository;
import bg.softuni.carhailrepair.service.UserService;
import bg.softuni.carhailrepair.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserSession userSession;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserSession userSession, UserRepository userRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail());
        if (existingUser.isPresent()) {
            return false;
        }

        boolean isFirstUser = userRepository.count() == 0;

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setUserRole(isFirstUser ? UserRole.ADMIN : UserRole.USER);

        User savedUser = userRepository.save(user);

        if (isFirstUser) {
            Admin admin = new Admin();
            admin.setUser(savedUser);
            adminRepository.save(admin);
        }

        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), byUsername.get().getPassword())) {
            return false;
        }

        userSession.login(byUsername.get().getId(), byUsername.get().getUsername());

        return true;
    }

    @Override
    public void logout() {
        userSession.logout();
    }
}
