package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.entities.Location;
import com.logonedigital.gestion_stock_g7.repositories.LocationRepo;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class LocationServiceImpl implements LocationService{
    private final LocationRepo locationRepo;

    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public Location addLocation(Location location) {
        location.setCreatedAt(Instant.now());
        return this.locationRepo.save(location);
    }

    @Override
    public Location updateLocation(Location location, Integer locationId) {
        return null;
    }
}
