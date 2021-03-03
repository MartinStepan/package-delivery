package bsc.packagedelivery.model;

public class Package {

    private String postCode;
    private double weight;
    private double fee;

    public Package(String postCode, double weight, double fee) {
        this.postCode = postCode;
        this.weight = weight;
        this.fee = fee;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Package updateWeightAndFee(double weight, double fee) {
        this.setWeight(getWeight() + weight);
        this.setFee(getFee() + fee);

        return this;
    }
}
