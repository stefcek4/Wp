package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> findAll() {
        return DataHolder.events;
    }

    public Optional<Event> findById(Long eventId) {
        return DataHolder.events.stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst();
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase())
                        || event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Event save(Event event) {
        event.setId((long) (DataHolder.events.size() + 1));
        DataHolder.events.add(event);
        return event;
    }

    public Event update(Long eventId, String name, String description, double popularityScore, Location location) {
        Optional<Event> existingEvent = findById(eventId);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(location);
            return event;
        }
        return null;
    }

    public void deleteById(Long eventId) {
        DataHolder.events.removeIf(event -> event.getId().equals(eventId));
    }
}
