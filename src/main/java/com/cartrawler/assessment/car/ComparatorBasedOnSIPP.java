package com.cartrawler.assessment.car;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorBasedOnSIPP implements Comparator<CarResult> {

    @Override
    public int compare(CarResult car1, CarResult car2) {

        String sipp1 = car1.getSippCode();
        String sipp2 = car2.getSippCode();
        if (sipp1.startsWith("M")) {
            return -2;
        }
        if (sipp1.startsWith("E")) {
            return -1;
        }
        if (sipp1.startsWith("C")) {
            return 1;
        }
        if (sipp2.startsWith("M")) {
            return -2;
        }
        if (sipp2.startsWith("E")) {
            return -1;
        }
        if (sipp2.startsWith("C")) {
            return 1;
        }
        return car1.getSippCode().compareTo(car2.getSippCode());
    }
}
