package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;
import be.vdab.muziek.exceptions.AlbumNotFoundException;
import be.vdab.muziek.services.AlbumService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("albums")
class AlbumController {
    private final AlbumService albumService;

    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    private record GewijzigdeScore(@Min(0) @Max(10) int newScore) {}
    private record AlbumNaamArtiestJaar(String naam, String artiestNaam, int jaar) {
        AlbumNaamArtiestJaar(Album album) {
            this(album.getNaam(), album.getArtiest().getNaam(), album.getJaar());
        }
    }
    private record AlbumFullInfo (String naam, String artiestNaam, int jaar, String labelNaam,
                                  LocalTime tijd, Set<Track> tracks) {
         AlbumFullInfo (Album album) {
             this (album.getNaam(), album.getArtiest().getNaam(), album.getJaar(), album.getLabel().getNaam(),
                     album.getTijd(), album.getTracks());
         }
    }


    @GetMapping
    Stream<AlbumNaamArtiestJaar> findAllWithArtiest() {
        return albumService.findAllWithArtiest()
                            .stream()
                            .map(AlbumNaamArtiestJaar::new);
    }

    @GetMapping
    Stream<AlbumNaamArtiestJaar> findAll() {
        return albumService.findAll()
                .stream()
                .map(AlbumNaamArtiestJaar::new);
    }

    @GetMapping("{id}")
    AlbumFullInfo findById (@PathVariable long id) {
        return albumService.findByIdAlbumWithFullInfo(id)
                            .map(AlbumFullInfo::new)
                            .orElseThrow(() -> new AlbumNotFoundException(id));
    }

    @GetMapping (params = "jaar")
    Stream<AlbumNaamArtiestJaar> findByJaarOrderByNaam(int jaar) {
        return albumService.findByJaarOrderByNaam(jaar)
                            .stream()
                            .map(AlbumNaamArtiestJaar::new);
    }

    @PatchMapping ("{id}/score")
    void wijzigScore(@PathVariable long id, @RequestBody @Valid GewijzigdeScore gewijzigdeScore) {
         albumService.wijzigScore(id, gewijzigdeScore.newScore);
    }


}
