package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.LocationRepo;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepo locationRepository;
    public LocationServiceImpl(LocationRepo locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }
}
