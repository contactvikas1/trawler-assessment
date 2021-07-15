package com.cartrawler.assessment.util;

import com.cartrawler.assessment.car.CarResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CarUtil {
    public static List<CarResult> sortCarOnMultiField(List<CarResult> unsortedList) {
        List<CarResult> sortedList = new ArrayList<>();

//        List<Log> resultantList = initialList.stream()
//                .collect(Collectors.groupingBy(l -> l.title + l.ID)).values().stream()                          // group according to title and id
//                .map(logs -> logs.stream().sorted(comparator).findFirst().get())                                // sort and take the first
//                .collect(Collectors.toList());


        Map<String, Set<CarResult>> map = unsortedList.stream().
                collect(groupingBy(e -> e.getDescription() + e.getSupplierName() + e.getSippCode() + e.getFuelPolicy(), toSet()));
        return sortedList;
    }
}
