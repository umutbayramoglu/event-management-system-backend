package yte.internship.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yte.internship.app.entity.Event;
import yte.internship.app.entity.User_;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User_, Long> {

	Optional<User_> findByUsername(String username);

	Optional<User_> findById(Long id);

	void deleteByUsername(String username);

	boolean existsByUsername(String username);

	@Query("SELECT user.createdEvents FROM User_ user WHERE user.username = ?1")
	Set<Event> findCreatedEventsByUsername(String username);

}
