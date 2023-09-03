package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtiestRepository extends JpaRepository<Artiest, Long> {

}
