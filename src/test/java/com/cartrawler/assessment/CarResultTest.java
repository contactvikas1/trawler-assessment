package com.cartrawler.assessment;

import static org.junit.Assert.assertTrue;

import com.cartrawler.assessment.car.CarResult;
import com.cartrawler.assessment.util.CarUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class CarResultTest {

    List<CarResult> CARS;

    @Before
    public void init() {
        CARS = new ArrayList();
        CARS.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, CarResult.FuelPolicy.FULLFULL));
        CARS.add(new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 93.67d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Volkswagen Golf", "NIZA", "CDMR", 18.07d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Audi A3 Diesel", "NIZA", "CDMD", 41.16d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Volkswagen Touran Diesel", "HERTZ", "IVMD", 55.47d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Mini Cooper", "NIZA", "EDMR", 16.75d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("BMW 2 Series Gran Tourer Diesel", "NIZA", "JVAD", 146.62d, CarResult.FuelPolicy.FULLFULL));
        // below is duplicate of 1st
        CARS.add(new CarResult("Volkswagen Polo", "NIZA", "EDMR", 22.65d, CarResult.FuelPolicy.FULLFULL));
        CARS.add(new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 43.98d, CarResult.FuelPolicy.FULLFULL));
        CARS.add(new CarResult("Renault Scenic Diesel", "ENTERPRISE", "JGAD", 115.58d, CarResult.FuelPolicy.FULLFULL));
        CARS.add(new CarResult("Volkswagen Up", "NIZA", "MDMR", 18.6d, CarResult.FuelPolicy.FULLFULL));
        // below is duplicate of 5th
        CARS.add(new CarResult("Volkswagen Golf", "NIZA", "CDMR", 33.06d, CarResult.FuelPolicy.FULLEMPTY));
        CARS.add(new CarResult("Audi A3 Diesel", "AVIS", "CDMD", 63.07d, CarResult.FuelPolicy.FULLFULL));
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    /**
     * Removal of duplicates on basis of duplicates = same make, model, supplier, SIPP, FuelPolicy
     */
    @Test
    public void removeDuplicateTestFromList() {

        List<CarResult> distinctResultList = CarUtil.removeDuplicate(CARS);
        Assert.assertTrue(CARS.size() == 15);
        // After two duplicate records removal
        Assert.assertTrue(distinctResultList.size() == 13);

    }

    /**
     *  Sort so that corporate cars appears first
     *  Corporate car are those whose supplier ares amoung : AVIS, BUDGET, ENTERPRISE, FIREFLY, HERTZ, SIXT, THRIFTY
     */
    @Test
    public void sortListByCorporsteAndNonCorporate() {
        List<CarResult> distinctResultList = CarUtil.removeDuplicate(CARS);

        Map<String, List<CarResult>> corporateCarFirst = CarUtil.sortListByCorporate(distinctResultList);
        List<CarResult> corporsteCarList=corporateCarFirst.get(CarResult.SUPPLIER_CATEGORY.CORPORATE.toString());
        List<CarResult> nonCorporsteCarList=corporateCarFirst.get(CarResult.SUPPLIER_CATEGORY.NONCORPORATE.toString());
        // corporate car is total 3
        Assert.assertTrue(corporsteCarList.size()==3);
        // corporate car is total 3
        Assert.assertTrue(nonCorporsteCarList.size()==10);
    }

    @Test
    public void renderEndToEndFinalList() {
        // TODO
    }
}
