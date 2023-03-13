package com.example.TaxiPark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.example.TaxiPark.model.RouteDto;
import com.example.TaxiPark.model.Routes;
import com.example.TaxiPark.service.RoutesDtoMapper;
import org.junit.jupiter.api.Test;

class RoutesDtoMapperTest {

  @Test()
  void testWorkOfDtoMapper() {
    RoutesDtoMapper routesDtoMapper = new RoutesDtoMapper();
    RouteDto actualDto = routesDtoMapper.toRouteDto(new Routes(1000, 1000), 2d);
    RouteDto expectedDto = new RouteDto(1000, 1000, 2d);
    assertEquals(actualDto, expectedDto);
  }

  @Test
  void negativeTestWorkOfDtoMapper() {
    RoutesDtoMapper routesDtoMapper = new RoutesDtoMapper();
    RouteDto actualDto = routesDtoMapper.toRouteDto(new Routes(1000, 1000), 2d);
    RouteDto expectedDto = new RouteDto(10100, 1000, 2d);
    assertNotEquals(actualDto, expectedDto);
  }
}
