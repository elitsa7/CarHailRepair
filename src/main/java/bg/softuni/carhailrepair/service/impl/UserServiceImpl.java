package bg.softuni.carhailrepair.service.impl;

import bg.softuni.carhailrepair.model.User;
import bg.softuni.carhailrepair.model.dtos.UserRegisterDTO;
import bg.softuni.carhailrepair.model.enums.UserRole;
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
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserSession userSession, UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterDTO data) {
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(data.getUsername(), data.getPassword());
        if (existingUser.isPresent()) {
            return false;
        }

        boolean isFirstUser = userRepository.count() == 0;

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setUserRole(isFirstUser ? UserRole.ADMIN : UserRole.USER);

        userRepository.save(user);
        return true;
    }
}
