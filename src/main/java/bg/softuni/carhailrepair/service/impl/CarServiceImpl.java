package bg.softuni.carhailrepair.service.impl;

import bg.softuni.carhailrepair.model.Car;
import bg.softuni.carhailrepair.model.User;
import bg.softuni.carhailrepair.model.dtos.car.SubmitCarDTO;
import bg.softuni.carhailrepair.repo.CarRepository;
import bg.softuni.carhailrepair.repo.UserRepository;
import bg.softuni.carhailrepair.service.CarService;
import bg.softuni.carhailrepair.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final ModelMapper modelMapper;
    private final Path uploadDir;

    public CarServiceImpl(CarRepository carRepository, UserSession userSession, UserRepository userRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.uploadDir = Paths.get("uploads");
        try {
            Files.createDirectories(uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    @Override
    public void saveCar(SubmitCarDTO submitCarDTO) {
        Car car = modelMapper.map(submitCarDTO, Car.class);
        MultipartFile imageFile = submitCarDTO.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String filename = imageFile.getOriginalFilename();
                Path filePath = uploadDir.resolve(filename);
                Files.copy(imageFile.getInputStream(), filePath);
                car.setImagePath(filePath.toString());
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + imageFile.getOriginalFilename(), e);
            }
        }

        long userId = userSession.getId();
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            car.setUser(optionalUser.get());

            carRepository.save(car);
        }
    }
}
