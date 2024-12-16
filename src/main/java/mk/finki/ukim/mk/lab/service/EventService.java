package mk.finki.ukim.mk.lab.service;
import mk.finki.ukim.mk.lab.model.Event;
import java.util.List;
import java.util.Optional;


public interface EventService {
        List<Event> listAll();
        Optional<Event>findById(Long eventId);
        void save(String name, String description, double popularityScore, Long locationId);
        void update(Long eventId, String name, String description, double popularityScore, Long locationId);
        void delete(Long eventId);
        List<Event> searchEvents(String searchText, Double minRating, Long locationId);
        public List<Event> findAllByLocation_Id(Long locationId);
}


