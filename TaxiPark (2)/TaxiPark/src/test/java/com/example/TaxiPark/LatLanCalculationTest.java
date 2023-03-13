package com.example.TaxiPark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.TaxiPark.model.LatLan;
import com.example.TaxiPark.model.Point;
import com.example.TaxiPark.service.GeoCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

class LatLanCalculationTest {

  GeoCodeService geoCodeService = new GeoCodeService();

  @Test
  void checkLatLanCalculationFromOdincovo() throws InterruptedException {
    Thread.sleep(4000);
    String responseWithAddress = "https://us1.locationiq.com/v1/search?format=json&key=pk.78c9e146d2d02a2623b0d87d073d673a&q=Одинцова 43 Минск";
    RestTemplate restTemplate1 = new RestTemplate();
    LatLan[] forObject = restTemplate1.getForObject(responseWithAddress, LatLan[].class);
    Point point1 = new Point(Double.parseDouble(forObject[0].getLat()), Double.parseDouble(forObject[0].getLon()));
    assertEquals(53.897762099999994, point1.getLat());
    assertEquals(27.442487869217217, point1.getLon());
  }

  @Test
  void negativeCheckLatLanCalculationFromKreml() {
    String responseWithAddress = "https://us1.locationiq.com/v1/search?format=json&key=pk.78c9e146d2d02a2623b0d87d073d673a&q=Москва, Россия, 103132";
    RestTemplate restTemplate1 = new RestTemplate();
    LatLan[] forObject = restTemplate1.getForObject(responseWithAddress, LatLan[].class);
    Point point1 = new Point(Double.parseDouble(forObject[0].getLat()), Double.parseDouble(forObject[0].getLon()));
    assertNotEquals(55, point1.getLat());
    assertNotEquals(37, point1.getLon());
  }

  @Test
  void checkLatLanCalculationFromKreml() throws InterruptedException {
    Thread.sleep(4000);
    String responseWithAddress = "https://us1.locationiq.com/v1/search?format=json&key=pk.78c9e146d2d02a2623b0d87d073d673a&q=Москва, Россия, 103132";
    RestTemplate restTemplate1 = new RestTemplate();
    LatLan[] forObject = restTemplate1.getForObject(responseWithAddress, LatLan[].class);
    Point point1 = new Point(Double.parseDouble(forObject[0].getLat()), Double.parseDouble(forObject[0].getLon()));
    System.out.println(point1.getLat());
    System.out.println(point1.getLon());
    assertEquals(55.7504461, point1.getLat());
    assertEquals(37.6174943, point1.getLon());
  }

  @Test()
  void shouldThrowException() throws InterruptedException {
    Thread.sleep(4000);
    String responseWithAddress = "https://us1.locationiq.com/v1/search?format=json&key=pk"
        + ".78c9e146d2d02a2623b0d87d073d673a&q= ";
    RestTemplate restTemplate1 = new RestTemplate();
    Throwable exception = assertThrows(HttpClientErrorException.class, () -> {
      LatLan[] forObject = restTemplate1.getForObject(responseWithAddress, LatLan[].class);
    });

    assertEquals("400 Bad Request: \"{\"error\":\"Invalid Request\"}\"", exception.getMessage());
  }

  @Test
  void testMethodInCode() {
    LatLan actual = geoCodeService.getCoordinates("Яна Чечота 6");
    LatLan expected = new LatLan("53.8474191", "27.4784383");
    assertEquals(actual, expected);
  }
}













