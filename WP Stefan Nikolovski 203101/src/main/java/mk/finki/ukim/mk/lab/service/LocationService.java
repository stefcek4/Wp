package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    public List<Location> findAll();
    Optional<Location> findById(Long id);
}
