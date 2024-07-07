package bg.softuni.carhailrepair.service.impl;

import bg.softuni.carhailrepair.model.Car;
import bg.softuni.carhailrepair.model.dtos.car.SubmitCarDTO;
import bg.softuni.carhailrepair.repo.CarRepository;
import bg.softuni.carhailrepair.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveCar(SubmitCarDTO submitCarDTO){
        Car car = modelMapper.map(submitCarDTO, Car.class);
        //TODO Image
        carRepository.save(car);
    }
}
