package yte.internship.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yte.internship.app.entity.Event;
import yte.internship.app.entity.UserEvent;
import yte.internship.app.entity.User_;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event,Long> {

    @Override
    Optional<Event> findById(Long id);

    @Override
    void deleteById(Long id);

    @Query("SELECT event.userEvents FROM Event event WHERE event.id = ?1")
    Set<UserEvent> findUserEventsByEventId(Long id);

    @Query("SELECT event.owner FROM Event event WHERE event.id = ?1 ")
    User_ findOwnerByEventId(Long id);
}
