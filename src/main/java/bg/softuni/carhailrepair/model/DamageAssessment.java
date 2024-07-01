package bg.softuni.carhailrepair.model;

import jakarta.persistence.*;

@Entity
@Table(name = "damage_assessment")
public class DamageAssessment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "assessment_details", columnDefinition = "TEXT")
    private String assessmentDetails;

    @Column(name = "estimated_price")
    private double estimatedPrice;

    public DamageAssessment() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getAssessmentDetails() {
        return assessmentDetails;
    }

    public void setAssessmentDetails(String assessmentDetails) {
        this.assessmentDetails = assessmentDetails;
    }

    public double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }
}
