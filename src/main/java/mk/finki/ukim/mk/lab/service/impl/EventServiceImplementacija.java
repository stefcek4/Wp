package mk.finki.ukim.mk.lab.service.impl;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepo;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.service.EventBookingService;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImplementacija implements EventService, EventBookingService {
    private final EventRepo eventRepository;
    private final LocationServiceImpl locationService;

    public EventServiceImplementacija(EventRepo eventRepository, LocationServiceImpl locationService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public void save(String name, String description, double popularityScore, Long locationId) {
        Location location = locationService.findById(locationId).orElse(null);
        Event event = new Event(name, description, popularityScore, location);
        eventRepository.save(event);
    }

    @Override
    public void update(Long eventId, String name, String description, double popularityScore, Long locationId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();

            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            Location location = locationService.findById(locationId).orElse(null);
            event.setLocation(location);

            eventRepository.save(event);
        } else {

            throw new IllegalArgumentException("Event not found with id " + eventId);
        }
    }


    @Override
    public void delete(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> searchEvents(String searchText, Double minRating, Long locationId) {
        return eventRepository.findAll().stream()
                .filter(event -> (searchText == null || event.getName().toLowerCase().contains(searchText.toLowerCase())||event.getDescription().toLowerCase().contains(searchText.toLowerCase())))
                .filter(event -> (minRating == null || event.getPopularityScore() >= minRating))
                .filter(event -> (locationId == null || event.getLocation().getId().equals(locationId)))
                .collect(Collectors.toList());
    }
    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }

}

