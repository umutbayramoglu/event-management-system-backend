package yte.internship.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yte.internship.app.entity.UserEvent;
import yte.internship.app.entity.UserEventId;
import java.util.List;

@Repository
public interface UserEventRepo extends JpaRepository<UserEvent, UserEventId> {

    @Override
    void deleteById(UserEventId userEventId);


    /*@Query("SELECT userEvent.user FROM UserEvent userEvent")
    List<UserEvent> findByUserEventIdEventId(Long eventId);*/



}