package bg.softuni.carhailrepair.model.dtos.offer;

public class OfferDTO {
    private Long offerId;
    private double offerAmount;

    public OfferDTO() {
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public double getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(double offerAmount) {
        this.offerAmount = offerAmount;
    }
}
