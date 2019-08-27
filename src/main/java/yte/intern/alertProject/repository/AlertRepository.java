package yte.intern.alertProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.alertProject.model.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Optional<Alert> findByName(String name);
    List<Alert> findAllByOrOrderByCreatedAt();
}
