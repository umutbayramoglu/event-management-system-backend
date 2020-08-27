package yte.internship.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.internship.app.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
