package yte.intern.alertProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.alertProject.model.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
