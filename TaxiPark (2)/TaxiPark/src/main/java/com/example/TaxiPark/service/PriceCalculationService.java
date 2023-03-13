package com.example.TaxiPark.service;

import com.example.TaxiPark.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PriceCalculationService {

    private final GeoCodeService geoCodeService;
    private final RoutesDtoMapper mapper;

    public PriceCalculationService(GeoCodeService geoCodeService, RoutesDtoMapper mapper) {
        this.geoCodeService = geoCodeService;
        this.mapper = mapper;
    }

    public RouteDto getPrice(String startAddress, String endAddress) {
        LatLan startPoint = geoCodeService.getCoordinates(startAddress);
        LatLan endPoint = geoCodeService.getCoordinates(endAddress);

        RouteDto route = calculate(startPoint, endPoint);
        route.setEndAddress(endAddress);
        route.setStartAddress(startAddress);
        return route;
    }


    public RouteDto calculate(LatLan startPoint, LatLan endPoint) {
        Point firstPoint = new Point(Double.parseDouble(startPoint.getLat()), Double.parseDouble(startPoint.getLon()));
        Point secondPoint = new Point(Double.parseDouble(endPoint.getLat()), Double.parseDouble(endPoint.getLon()));
        List<Point> points = new ArrayList<>();
        int[] a = new int[1];
        a[0] = 0;
        int[] b = new int[1];
        b[0] = 1;
        points.add(firstPoint);
        points.add(secondPoint);
        Data data = new Data(points, a, b);

        String fooResourceUrl
                = "https://routing.api.2gis.com/get_dist_matrix?key=44734abd-04ee-4ab5-82b0-299861484b9a&version=2.0";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Data> request = new HttpEntity<>(data);
        ResponseEntity<Answer> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, Answer.class);

        return calculatePrice(Objects.requireNonNull(response.getBody()));
    }


    public RouteDto calculatePrice(Answer answer) {
        Routes route = answer.getRoutes().get(0);
        Double priceByDistance = calculateByDistance(route.getDistance());
        Double priceByTime = calculateByTime(route.getDuration());

        if (priceByDistance > priceByTime) {
            return mapper.toRouteDto(route, priceByDistance);
        }

        return mapper.toRouteDto(route, priceByTime);
    }


    public Double calculateByDistance(Integer distance) {
        return distance / 1000 * 0.2;
    }

    public Double calculateByTime(Integer time) {
        return time / 60 * 0.3;
    }
}
