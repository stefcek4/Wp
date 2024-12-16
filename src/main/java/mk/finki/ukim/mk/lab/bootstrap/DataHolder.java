package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepo;
import mk.finki.ukim.mk.lab.repository.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static List<Event>events=new ArrayList<>();
    public static List<Location>locations=new ArrayList<>();
    @Autowired
    private LocationRepo locationRepository;

    @Autowired
    private EventRepo eventRepository;
    @PostConstruct
    public void init() {

        if (locationRepository.count() == 0) {
            locations.add(new Location("Arena Filip II", "Skopje", "30,000", "Glaven sportski stadion"));
            locations.add(new Location("Dramski Teatar", "Centar, Skopje", "500", "Teatar za pretstavi - drama"));
            locations.add(new Location("Univerzalna Sala", "Karposh, Skopje", "2000", "Sala za koncerti i nastani"));
            locations.add(new Location("Sky Bar", "Karposh, Skopje", "100", "Ekskluziven koktel bar"));
            locations.add(new Location("Pelister Park", "Pelister, Makedonija", "Otvoren prostor", "Priroda i planinarenje"));


            for (Location location : locations) {
                locationRepository.save(location);
            }
        } else {

            locations = locationRepository.findAll();
        }


        if (eventRepository.count() == 0) {
            events.add(new Event("Fudbalski spektakl", "MKD vs Italy", 9.0, locations.get(0)));
            events.add(new Event("Komedi Show", "STAND UP COMEDY - NIKOLA TODOROV", 8.0, locations.get(1)));
            events.add(new Event("Muzichki Nastan", "Tose Proeski Tribute Concert", 9.0, locations.get(2)));
            events.add(new Event("Drama na Scena", "Pod Senkite na Mostot", 9.0, locations.get(1)));
            events.add(new Event("Sportski Dogovor", "Polumaraton Skopje 2024", 8.0, locations.get(0)));
            events.add(new Event("Film Proekcija", "'Beyond the Horizon' - Anne Hathaway, Chris Evans", 7.0, locations.get(1)));
            events.add(new Event("Otvoranje na Lounge", "Sky Bar Grand Opening", 6.0, locations.get(3)));
            events.add(new Event("Proslava za Noќ na Veshtenki", "Halloween Bash Skopje 2024", 8.0, locations.get(2)));
            events.add(new Event("Festival na Umetnost", "Skopje Craft Beer Festival", 7.0, locations.get(0)));
            events.add(new Event("Live Performance", "Plavi Orkestar - Muzichka Nostalgiја", 7.0, locations.get(2)));


            for (Event event : events) {
                eventRepository.save(event);
            }
        } else {

            events = eventRepository.findAll();
        }
    }
}
