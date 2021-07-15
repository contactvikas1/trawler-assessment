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

//        List<Log> resultantList = initialList.stream()
//                .collect(Collectors.groupingBy(l -> l.title + l.ID)).values().stream()                          // group according to title and id
//                .map(logs -> logs.stream().sorted(comparator).findFirst().get())                                // sort and take the first
//                .collect(Collectors.toList());


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

        //    List<CarResult> distinctList = new ArrayList(map.values());
//        for (CarResult carResult : distinctResultList) {
//            System.out.println(carResult.getSupplierName());
//        }

        //     Collections.sort(distinctList, new CorporateCarResultComparator());


//
        Map<Boolean, List<CarResult>> map1 = distinctResultList.stream().collect(Collectors.groupingBy(item -> corporateCarList.contains(item.getSupplierName()), toList()));

        List<CarResult> unCorporatedCar = map1.get(false);
        List<CarResult> corporatedCar = map1.get(true);

//        Collections.sort(corporatedCar,Comparator.comparing(item->item.getSippCode().startsWith("M")).
//                                                    thenComparing()
//
//        return Comparator
//                .comparing(l->l.getCourse().getTeacher().getAge(), Comparator.reverseOrder())
//                .thenComparing(l->l.getCourse().getStudentSize());



//        Person minByAge = corporatedCar.stream()
//                .collect(Comparator.comparing(CarResult::getSippCode.andThen(PersonalDetail::getBirthDate).andThen(BirthDate::getBirthdate))).get();


   //     Collections.sort(corporatedCar,new ComparatorBasedOnSIPP());


        return sortedList;
    }
}


//    public static IEnumerable<CarResult> doSorting()
//    {
//        List<CarResult> merged = new List<CarResult>();
//
//
//        CarResultList carListObject = new CarResultList();
//        var carList = carListObject.GetList().ToList();
//
//        var filteredList = (carListObject.GetList()
//                .GroupBy(y => y.supplierName + y.sippCode + y.description + y.rentalCost + y.fuelPolicy)
//                .Select(item => item.First())).ToList();
//
//        var _filteredList = (filteredList.GroupBy(item => corporateCar.Contains(item.supplierName))
//                .Select(item => item)).ToList();
//
//        var nonCorporateCarList = _filteredList[0].ToList()
//                .OrderBy(item => item.sippCode.StartsWith("M"))
//                .ThenBy(item => item.sippCode.StartsWith("E"))
//                .ThenBy(item => item.sippCode.StartsWith("C"))
//                .Reverse()
//            .ToList()
//            .OrderBy(item => item.rentalCost)
//                .ToList();
//
//        var corporateCarList = _filteredList[1].ToList()
//                .OrderBy(item => item.sippCode.StartsWith("M"))
//                .ThenBy(item => item.sippCode.StartsWith("E"))
//                .ThenBy(item => item.sippCode.StartsWith("C"))
//                .Reverse()
//            .OrderBy(item => item.rentalCost)
//                .ToList();
//
//        merged.AddRange(corporateCarList);
//        merged.AddRange(nonCorporateCarList);
//
//        return merged;
//    }