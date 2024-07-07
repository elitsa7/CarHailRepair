package bg.softuni.carhailrepair.controller;

import bg.softuni.carhailrepair.model.dtos.UserLoginDTO;
import bg.softuni.carhailrepair.model.dtos.UserRegisterDTO;
import bg.softuni.carhailrepair.repo.UserRepository;
import bg.softuni.carhailrepair.service.UserService;
import bg.softuni.carhailrepair.util.UserSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserSession userSession;
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserSession userSession, UserRepository userRepository, UserService userService) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginData")
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegisterDTO userRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerData", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

            return "redirect:/register";
        }

        boolean successRegistration = userService.register(userRegisterDTO);

        if (!successRegistration) {
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Valid UserLoginDTO userLoginDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);

            return "redirect:/login";
        }

        boolean successLogin = userService.login(userLoginDTO);

        if (!successLogin) {
            redirectAttributes.addFlashAttribute("loginError", true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }


}
