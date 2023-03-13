package com.example.TaxiPark.service;

import com.example.TaxiPark.model.entyti.Driver;
import com.example.TaxiPark.model.entyti.DriverStatus;
import com.example.TaxiPark.repository.DriverRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepos driverRepos;

    public DriverService(DriverRepos driverRepos) {
        this.driverRepos = driverRepos;
    }

    public List<Driver> getAllDriverByStatus(DriverStatus status) {
        return driverRepos.findAllByStatus(status);
    }

    public Driver getDriverByID(Long id) {
        return driverRepos.findById(id).get();
    }

}
