package com.example.TaxiPark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.TaxiPark.service.GeoCodeService;
import com.example.TaxiPark.service.PriceCalculationService;
import com.example.TaxiPark.service.RoutesDtoMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PriceCalculationTest {

  @Mock
  private GeoCodeService geoCodeService;

  @InjectMocks
  private RoutesDtoMapper mapper;
  PriceCalculationService priceCalculationService = new PriceCalculationService(geoCodeService, mapper);

  @Test
  void testCalculationByTime() {
    assertEquals(4.8, priceCalculationService.calculateByTime(1000));
  }

  @Test
  void testCalculationByDistance() {
    assertEquals(0.2, priceCalculationService.calculateByDistance(1000));
  }

  @Test
  void negativeTestCalculationByTime() {
    assertNotEquals(4.6, priceCalculationService.calculateByTime(1000));
  }

  @Test
  void negativeTestCalculationByDistance() {
    assertNotEquals(0.2, priceCalculationService.calculateByDistance(0));
  }

  @Test()
  void shouldThrowException() {
    Throwable exception = assertThrows(NumberFormatException.class, () -> {
      priceCalculationService.calculateByDistance(Integer.valueOf("wrefwef"));
    });
    assertEquals("For input string: \"wrefwef\"", exception.getMessage());
  }
}
