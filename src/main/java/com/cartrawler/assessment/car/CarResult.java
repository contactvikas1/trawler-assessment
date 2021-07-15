package com.cartrawler.assessment.car;

public class CarResult {
    private final String description;
    private final String supplierName;
    private final String sippCode;
    private final double rentalCost;
    private final FuelPolicy fuelPolicy;

	public enum FuelPolicy {
        FULLFULL,
        FULLEMPTY};

    public CarResult(String description, String supplierName, String sipp, double cost, FuelPolicy fuelPolicy) {
        this.description = description;
        this.supplierName = supplierName;
        this.sippCode = sipp;
        this.rentalCost = cost;
        this.fuelPolicy = fuelPolicy;
    }


    public String getDescription() {
        return this.description;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public String getSippCode() {
        return this.sippCode;
    }

    public double getRentalCost() {
        return this.rentalCost;
    }

    public FuelPolicy getFuelPolicy() {
        return this.fuelPolicy;
    }

    public String toString() {
        return this.supplierName + " : " +
                this.description + " : " +
                this.sippCode + " : " +
                this.rentalCost + " : " +
                this.fuelPolicy;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CarResult)) {
            return false;
        }
        CarResult carR = (CarResult) obj;
        return (description.equals(carR.getDescription()) && supplierName.equals(carR.getSupplierName()) && sippCode.equals(carR.getSippCode()) && fuelPolicy.equals(carR.getFuelPolicy()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 89 * hash + (this.supplierName != null ? this.supplierName.hashCode() : 0);
        hash = 89 * hash + (this.sippCode != null ? this.sippCode.hashCode() : 0);
        hash = 89 * hash + (this.fuelPolicy != null ? this.fuelPolicy.hashCode() : 0);
        return hash;
    }
}
