package fatshaw.experiment.jpa.sample.repo;

import fatshaw.experiment.jpa.sample.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
