package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {

    public List<Location> findAll() {
        return DataHolder.locations;
    }

    public Optional<Location> findById(Long locationId) {
        return DataHolder.locations.stream().filter(location -> location.getId().equals(locationId)).findFirst();
    }

}
