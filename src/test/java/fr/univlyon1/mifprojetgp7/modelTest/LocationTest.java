package fr.univlyon1.mifprojetgp7.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LocationTest {

    Location locationT = new Location();

    @Test
    public void TestLocationCategoryName() {
        System.out.println(">> TEST: Location Category Name <<");

        locationT.setCategoryName("Sport");
        assertEquals("Sport", locationT.getCategoryName());
        locationT.setCategoryName(null);
    }

    @Test
    public void TestLocationRoadName() {
        System.out.println(">> TEST: Location Road Name <<");

        locationT.setRoadName("roadTest");
        assertEquals("roadTest", locationT.getRoadName());
        locationT.setRoadName(null);
    }

    @Test
    public void TestLocationComplement() {
        System.out.println(">> TEST: Location Complement <<");

        locationT.setComplement("test");
        assertEquals("test", locationT.getComplement());
        locationT.setComplement(null);
    }

    @Test
    public void TestLocationPostalCode() {
        System.out.println(">> TEST: Location Postal Code <<");

        locationT.setPostalCode(69001);
        assertEquals(69001, locationT.getPostalCode());
        locationT.setPostalCode(-1);
    }

    @Test
    public void TestLocationCityName() {
        System.out.println(">> TEST: Location City Name <<");

        locationT.setCityName("cityTest");
        assertEquals("cityTest", locationT.getCityName());
        locationT.setCityName(null);
    }

    @Test
    public void TestLocationCountryName() {
        System.out.println(">> TEST: Location Postal Code <<");

        locationT.setCountryName("countryTest");
        assertEquals("countryTest", locationT.getCountryName());
        locationT.setCountryName(null);
    }
}
