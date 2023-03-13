package com.example.TaxiPark.service;

import com.example.TaxiPark.model.RouteDto;
import com.example.TaxiPark.model.Routes;
import org.springframework.stereotype.Component;

@Component
public class RoutesDtoMapper {

    public RouteDto toRouteDto(Routes routes, Double price) {
        return new RouteDto(routes.getDistance(), routes.getDuration(), price);
    }

}
