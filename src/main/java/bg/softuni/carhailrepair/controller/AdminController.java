package bg.softuni.carhailrepair.controller;

import bg.softuni.carhailrepair.model.dtos.user.UserDTO;
import bg.softuni.carhailrepair.service.AdminService;
import bg.softuni.carhailrepair.service.UserService;
import bg.softuni.carhailrepair.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;
    private final UserSession userSession;

    public AdminController(AdminService adminService, UserService userService, UserSession userSession) {
        this.adminService = adminService;
        this.userService = userService;
        this.userSession = userSession;
    }

    @GetMapping("/user-list")
    public String listUsers(Model model) {
        Long userId = userSession.getId();
        if (!userService.isUserAdmin(userId)) {
            model.addAttribute("error", "Access Denied: You do not have permission to view this page!");
            return "user-list";
        }
        List<UserDTO> users = adminService.listUsers();
        model.addAttribute("users", users);
        return "user-list";
    }
}
