package bg.softuni.carhailrepair.model.dtos.car;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class SubmitCarDTO {
    @NotBlank(message = "Car make is required!")
    private String make;

    @NotBlank(message = "Car model is required!")
    private String model;

    @Min(value = 1900, message = "Invalid car year!")
    private int year;

    @NotNull(message = "Please attach the photo!")
    private MultipartFile image;

    @NotBlank(message = "Damage description is required!")
    private String damageDescription;

    public SubmitCarDTO() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
