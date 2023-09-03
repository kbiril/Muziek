package be.vdab.muziek.domain;
import be.vdab.muziek.exceptions.ScoreNotValidException;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    private long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "labelId")
    private Label label;

    private String naam;
    private int jaar;
    private long barcode;
    private int score;

    @ElementCollection
    @CollectionTable (name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    @OrderBy("naam")
    private Set<Track> tracks;


    public Album(Artiest artiest, Label label, String naam, int jaar, long barcode, int score) {
        this.artiest = artiest;
        this.label = label;
        this.naam = naam;
        this.jaar = jaar;
        this.barcode = barcode;
        this.score = score;
        tracks = new LinkedHashSet<>();
    }

    protected Album() {}

    public long getId() {
        return id;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Label getLabel() {
        return label;
    }

    public String getNaam() {
        return naam;
    }

    public int getJaar() {
        return jaar;
    }

    public long getBarcode() {
        return barcode;
    }

    public int getScore() {
        return score;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    public void setScore(int score) {
        if (score >= 0 && score <= 10) {
            this.score = score;
        } else {
            throw new ScoreNotValidException();
        }
    }

    public LocalTime getTijd () {
        return getTracks().stream()
                          .map(track -> (LocalTime) track.getTijd())
                          .reduce(LocalTime.of(0,0, 0), (som, tijd) -> som.plusHours(tijd.getHour())
                                                                                              .plusMinutes(tijd.getMinute())
                                                                                              .plusSeconds(tijd.getSecond()));
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Album album && barcode == album.barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode);
    }
}
