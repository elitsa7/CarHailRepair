package bg.softuni.carhailrepair.controller;

import bg.softuni.carhailrepair.model.dtos.user.UserDTO;
import bg.softuni.carhailrepair.model.enums.UserRole;
import bg.softuni.carhailrepair.service.AdminService;
import bg.softuni.carhailrepair.service.UserService;
import bg.softuni.carhailrepair.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    private final AdminService adminService;
    private final UserSession userSession;

    public AdminController(AdminService adminService, UserSession userSession) {
        this.adminService = adminService;
        this.userSession = userSession;
    }

    @GetMapping("/user-list")
    public String listUsers(Model model) {
        Long userId = userSession.getId();
        if (!adminService.isUserAdmin(userId)) {
            model.addAttribute("error", "Access Denied: You do not have permission to view this page!");
            return "user-list";
        }
        List<UserDTO> users = adminService.listUsers();
        model.addAttribute("users", users);
        model.addAttribute("roles", UserRole.values());
        return "user-list";
    }
    @PostMapping("/set-role")
    public String setRole(@RequestParam Long userId, @RequestParam UserRole role, RedirectAttributes redirectAttributes) {
        Long currentUserId = userSession.getId();
        if (!adminService.isUserAdmin(currentUserId)) {
            redirectAttributes.addFlashAttribute("error", "Access Denied: You do not have permission to perform this action.");
            return "redirect:/user-list";
        }
        adminService.setUserRole(userId, role);
        redirectAttributes.addFlashAttribute("message", "User role updated successfully.");
        return "redirect:/user-list";
    }
    // TODO MISS MATCH WHEN SET USER TO DELETE IT FROM ADMIN TABLE
}
