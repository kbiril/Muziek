package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.exceptions.AlbumNotFoundException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAllWithArtiest() { return albumRepository.findAllWithArtiest(); }

    public List<Album> findAll() { return albumRepository.findAll(Sort.by("naam")); }

    public Optional<Album> findByIdAlbumWithFullInfo(long id) { return albumRepository.findByIdAlbumWithFullInfo(id); }

    public List<Album> findByJaarOrderByNaam(int jaar) {return albumRepository.findByJaarOrderByNaam(jaar); }

    @Transactional
    public void wijzigScore (long id, int newScore) {
        albumRepository.findAndLockById(id)
                .orElseThrow(() -> new AlbumNotFoundException(id))
                .setScore(newScore);
    }
}
