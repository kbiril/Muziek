package be.vdab.muziek.domain;

import be.vdab.muziek.exceptions.ArtiestHeeftDezeAlbumAlException;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    private long id;
    private String naam;
    @OneToMany(mappedBy = "artiest")
    @OrderBy("naam, jaar")
    private Set<Album> albumsPerArtiest;

    public Artiest(String naam) {
        this.naam = naam;
        albumsPerArtiest = new LinkedHashSet<>();
    }
    protected Artiest() {}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Album> getAlbumsPerArtiest() {
        return Collections.unmodifiableSet(albumsPerArtiest);
    }

    public void artiestVoegAlbumToe (Album album) {
        if (!albumsPerArtiest.add(album)) {
            throw new ArtiestHeeftDezeAlbumAlException();
        }
    }
}
