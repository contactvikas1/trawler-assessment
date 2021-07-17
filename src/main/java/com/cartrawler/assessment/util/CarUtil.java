package com.cartrawler.assessment.util;

import com.cartrawler.assessment.car.CarResult;
import com.cartrawler.assessment.car.ComparatorBasedOnSIPP;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/***
 * Utilities class for different method to perform various actions on given list
 *
 */
public class CarUtil {


    /**
     * Removes all duplicate on basis of duplicates = same make, model, supplier, SIPP, FuelPolicy
     *
     * @param listWithDuplicate PASSED LIST WITH DUPLICATE INSIDE
     * @return list with all duplicate removed
     */
    public static List<CarResult> removeDuplicate(List<CarResult> listWithDuplicate) {
        List<CarResult> distinctResultList = new ArrayList<>();

        Map<String, List<CarResult>> distinctMap = listWithDuplicate.stream().
                collect(groupingBy(CarResult::getDuplicatesFields, toList()));

        CarResult carResult = null;
        for (String key : distinctMap.keySet()) {
            carResult = new CarResult(distinctMap.get(key).get(0).getDescription(), distinctMap.get(key).get(0).getSupplierName(), distinctMap.get(key).get(0).getSippCode(), distinctMap.get(key).get(0).getRentalCost(), distinctMap.get(key).get(0).getFuelPolicy());
            distinctResultList.add(carResult);
        }
        return distinctResultList;
    }

    /**
     * Sort the List by Corporate 0supplier at first
     *
     * @param distinctResultList
     * @return
     */
    public static Map<String, List<CarResult>> sortListByCorporate(List<CarResult> distinctResultList) {
        List<CarResult> sorByCarporateResultList = new ArrayList<>();
        List<CarResult> sorByUncorporateResultList = new ArrayList<>();
        List<String> corporateCarList = Arrays.asList("AVIS", "BUDGET", "ENTERPRISE", "FIREFLY", "HERTZ", "SIXT", "THRIFTY");

        Map<Boolean, List<CarResult>> corporateUncorporateMap = distinctResultList.stream().collect(Collectors.groupingBy(item -> corporateCarList.contains(item.getSupplierName()), toList()));
        sorByCarporateResultList = corporateUncorporateMap.get(true);
        sorByUncorporateResultList = corporateUncorporateMap.get(false);
        Map<String, List<CarResult>> sortByCorporateAndUncorporateMap = new HashMap<>();

        sortByCorporateAndUncorporateMap.put(CarResult.SUPPLIER_CATEGORY.CORPORATE.toString(), sorByCarporateResultList);
        sortByCorporateAndUncorporateMap.put(CarResult.SUPPLIER_CATEGORY.NONCORPORATE.toString(), sorByUncorporateResultList);

        return sortByCorporateAndUncorporateMap;
    }

    /**
     * The sortCarOnMultiple field method this is the main method calling other methods
     *
     * @param unsortedList
     * @return
     */
    public static List<CarResult> sortCarOnMultiField(List<CarResult> unsortedList) {
        List<CarResult> sortedList = new ArrayList<>();

        // calling method to remove duplicate
        List<CarResult> distinctResultList = removeDuplicate(unsortedList);

        Map<String, List<CarResult>> sortByCorporateAndUncorporateMap = sortListByCorporate(distinctResultList);

        List<CarResult> corporateFinalList = sortByCorporateAndUncorporateMap.get(CarResult.SUPPLIER_CATEGORY.CORPORATE.toString())
                .stream().sorted(new ComparatorBasedOnSIPP()
                        .reversed()).sorted(Comparator.comparingDouble(CarResult::getRentalCost))
                .collect(Collectors.toList());

        List<CarResult> nonCorporateFinalList = sortByCorporateAndUncorporateMap.get(CarResult.SUPPLIER_CATEGORY.NONCORPORATE.toString())
                .stream().sorted(new ComparatorBasedOnSIPP()
                        .reversed()).sorted(Comparator.comparingDouble(CarResult::getRentalCost))
                .collect(Collectors.toList());

        corporateFinalList.addAll(nonCorporateFinalList);

        sortedList.addAll(corporateFinalList);

        return sortedList;
    }
}
