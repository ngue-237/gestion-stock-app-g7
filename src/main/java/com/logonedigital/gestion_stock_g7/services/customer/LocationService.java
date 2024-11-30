package com.logonedigital.gestion_stock_g7.services.customer;

import com.logonedigital.gestion_stock_g7.entities.Location;

public interface LocationService {
    Location addLocation(Location location);
    Location updateLocation(Location location, Integer locationId);

}
