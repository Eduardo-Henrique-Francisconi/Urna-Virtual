package urna.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import urna.com.app.entity.Eleitor;

import java.util.List;

@Repository
public interface EleitorRepository extends JpaRepository<Eleitor, Long> {
    List<Eleitor> findByStatusNot(Eleitor.Status status);
}
