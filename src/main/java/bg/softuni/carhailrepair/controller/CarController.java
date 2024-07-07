package bg.softuni.carhailrepair.controller;

import bg.softuni.carhailrepair.model.dtos.car.SubmitCarDTO;
import bg.softuni.carhailrepair.service.CarService;
import bg.softuni.carhailrepair.util.UserSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarController {
    private final UserSession userSession;
    private final CarService carService;

    public CarController(UserSession userSession, CarService carService) {
        this.userSession = userSession;
        this.carService = carService;
    }

    @ModelAttribute("submitCar")
    public SubmitCarDTO submitCarDTO(){
        return new SubmitCarDTO();
    }

    @GetMapping("submit-car")
    public String submitCar() {
        if(!userSession.isLoggedIn()){
            return "redirect:/login";
        }
        return "submit-car";
    }

    @PostMapping("submit-car")
    public String doSubmitCar(@Valid SubmitCarDTO submitCarDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if(!userSession.isLoggedIn()){
            return "redirect:/login";
        }


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.submitCar", bindingResult);
            redirectAttributes.addFlashAttribute("submitCar", submitCarDTO);
            return "redirect:/submit-car";
        }

        carService.saveCar(submitCarDTO);

        return "redirect:/home";
    }

}
