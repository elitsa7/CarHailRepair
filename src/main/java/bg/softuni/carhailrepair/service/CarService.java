package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.car.SubmitCarDTO;
import bg.softuni.carhailrepair.model.dtos.car.UserCarDTO;
import jakarta.transaction.Transactional;

import java.util.List;


public interface CarService {
    @Transactional
    void saveCar(SubmitCarDTO submitCarDTO);

    List<UserCarDTO> getUserCars(Long userId);
}
