package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;

import be.vdab.muziek.exceptions.ArtiestNotFoundException;
import be.vdab.muziek.services.ArtiestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Stream;
@RestController
@RequestMapping("artiesten")
class ArtiestController {
    private final ArtiestService artiestService;

    ArtiestController(ArtiestService artiestService) {
        this.artiestService = artiestService;
    }

    private record AlbumNaamJaar(String naam, int jaar) {
        AlbumNaamJaar(Album album) {
            this(album.getNaam(), album.getJaar());
        }
    }

    @GetMapping("{id}/albums")
    Stream<AlbumNaamJaar> findAlbumsVan(@PathVariable long id) {
        return artiestService.findById(id)
                            .orElseThrow(() -> new ArtiestNotFoundException(id))
                            .getAlbumsPerArtiest()
                            .stream()
                            .map(AlbumNaamJaar::new);
    }
}
