package com.cartrawler.assessment.car;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CorporateCarResultComparator implements Comparator<CarResult> {

    private static List<String> corporateCarList = Arrays.asList("AVIS", "BUDGET", "ENTERPRISE", "FIREFLY", "HERTZ", "SIXT", "THRIFTY");

    @Override
    public int compare(CarResult car1, CarResult car2) {
        String suppName1 = car1.getSupplierName();
        String suppName2 = car2.getSupplierName();
        if (corporateCarList.contains(suppName1)) {
            return -1;
        }
        if (corporateCarList.contains(suppName2)) {
            return 1;
        }
        return car1.getSupplierName().compareTo(car2.getSupplierName());
    }
}
