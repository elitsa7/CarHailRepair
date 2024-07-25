package bg.softuni.carhailrepair.model;

import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Long offerId;

    @Column(name = "offer_amount", nullable = false)
    private double offerAmount;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    public Offer() {

    }

    public Offer(Car car, User admin, double offerAmount) {
        this.car = car;
        this.admin = admin;
        this.offerAmount = offerAmount;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public double getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(double offerAmount) {
        this.offerAmount = offerAmount;
    }
}
