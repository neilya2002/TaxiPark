package com.example.TaxiPark.service;

import com.example.TaxiPark.model.LatLan;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoCodeService {

    public LatLan getCoordinates(String address) {
        String responseWithAddress = "https://us1.locationiq.com/v1/search?format=json&key=pk.78c9e146d2d02a2623b0d87d073d673a&q=" + address;
        RestTemplate restTemplate = new RestTemplate();
        LatLan[] latlan = restTemplate.getForObject(responseWithAddress, LatLan[].class);
        return latlan[0];
    }

}
