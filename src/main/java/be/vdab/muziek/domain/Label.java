package be.vdab.muziek.domain;

import be.vdab.muziek.exceptions.LabelHeeftDezeAlbumAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label {
    @Id
    private long id;
    private String naam;

    @OneToMany(mappedBy = "label")
    @OrderBy("naam, jaar")
    private Set<Album> albumsPerLabel;

    public Label(String naam) {
        this.naam = naam;
        albumsPerLabel = new LinkedHashSet<>();
    }

    protected Label() {}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }


    public Set<Album> getAlbumsPerLabel() {
        return Collections.unmodifiableSet(albumsPerLabel);
    }

    public void labelVoegAlbumToe(Album album) {
        if (!albumsPerLabel.add(album)) {
            throw new LabelHeeftDezeAlbumAlException();
        }
    }
}
