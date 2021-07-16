package com.cartrawler.assessment.util;

import com.cartrawler.assessment.car.CarResult;
import com.cartrawler.assessment.car.ComparatorBasedOnSIPP;
import com.cartrawler.assessment.car.CorporateCarResultComparator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CarUtil {
    private static List<String> corporateCarList = Arrays.asList("AVIS", "BUDGET", "ENTERPRISE", "FIREFLY", "HERTZ", "SIXT", "THRIFTY");

    public static List<CarResult> sortCarOnMultiField(List<CarResult> unsortedList) {
        List<CarResult> sortedList = new ArrayList<>();

        Map<String, List<CarResult>> map = unsortedList.stream().
                collect(groupingBy(CarResult::getSortedFields, toList()));

        List<CarResult> distinctResultList = new ArrayList<>();
        CarResult cr = null;
        for (String s : map.keySet()) {
            List<CarResult> carList = map.get(s);
            System.out.println(carList);
            cr = new CarResult(carList.get(0).getDescription(), carList.get(0).getSupplierName(), carList.get(0).getSippCode(), carList.get(0).getRentalCost(), carList.get(0).getFuelPolicy());
            distinctResultList.add(cr);
        }

        Map<Boolean, List<CarResult>> map1 = distinctResultList.stream().collect(Collectors.groupingBy(item -> corporateCarList.contains(item.getSupplierName()), toList()));

        List<CarResult> unCorporatedCar = map1.get(false);
        List<CarResult> corporatedCar = map1.get(true);

        List<CarResult> uncorporatedFinalList = unCorporatedCar.stream().sorted(new ComparatorBasedOnSIPP()
                .reversed()).sorted(Comparator.comparingDouble(CarResult::getRentalCost))
                .collect(Collectors.toList());


        List<CarResult> corporatedFinalList = corporatedCar.stream().sorted(new ComparatorBasedOnSIPP()
                .reversed()).sorted(Comparator.comparingDouble(CarResult::getRentalCost))
                .collect(Collectors.toList());
        corporatedFinalList.addAll(uncorporatedFinalList);
        sortedList.addAll(corporatedFinalList);

        return sortedList;
    }
}
