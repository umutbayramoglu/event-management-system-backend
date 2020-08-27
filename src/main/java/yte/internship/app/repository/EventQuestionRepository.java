package yte.internship.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yte.internship.app.entity.EventQuestion;

@Repository
public interface EventQuestionRepository extends JpaRepository<EventQuestion,Long> {
}
