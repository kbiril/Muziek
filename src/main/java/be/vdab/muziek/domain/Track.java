package be.vdab.muziek.domain;

import jakarta.persistence.Embeddable;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
public class Track {
    private String naam;
    private LocalTime tijd;

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track track)) return false;
        return Objects.equals(naam, track.naam) && Objects.equals(tijd, track.tijd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, tijd);
    }
}
