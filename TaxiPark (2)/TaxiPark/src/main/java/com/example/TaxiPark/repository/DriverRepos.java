package com.example.TaxiPark.repository;

import com.example.TaxiPark.model.entyti.Driver;
import com.example.TaxiPark.model.entyti.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepos extends JpaRepository<Driver,Long> {

    List<Driver> findAllByStatus(DriverStatus status);

}
