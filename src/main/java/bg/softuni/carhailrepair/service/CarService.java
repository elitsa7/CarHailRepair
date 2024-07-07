package bg.softuni.carhailrepair.service;

import bg.softuni.carhailrepair.model.dtos.car.SubmitCarDTO;
import jakarta.transaction.Transactional;

import java.io.IOException;

public interface CarService {
    @Transactional
    void saveCar(SubmitCarDTO submitCarDTO);
}
