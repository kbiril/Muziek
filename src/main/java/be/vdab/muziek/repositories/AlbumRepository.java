package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("select a from Album a join fetch a.artiest order by a.naam")
    List<Album> findAllWithArtiest();

    @Override
    @EntityGraph (attributePaths = "artiest")
    List<Album> findAll (Sort sort);

    @Query("select a from Album a join fetch a.artiest join fetch a.label join fetch a.tracks where a.id = :id")
    Optional<Album> findByIdAlbumWithFullInfo(long id);

    @EntityGraph(attributePaths = "artiest")
    List<Album> findByJaarOrderByNaam(int jaar);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query ("select a from Album a where a.id = :id")
    Optional<Album> findAndLockById(long id);

}
