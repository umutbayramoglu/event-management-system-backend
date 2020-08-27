package yte.internship.app.entity.mapper;

import org.mapstruct.Mapper;
import yte.internship.app.entity.Event;
import yte.internship.app.entity.EventRegistrationQuestion;
import yte.internship.app.entity.dto.EventDTO;
import yte.internship.app.entity.dto.EventRegistrationQuestionDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventRegistrationQuestionMapper {


    EventRegistrationQuestionDTO mapToDto(EventRegistrationQuestion question);

    List<EventRegistrationQuestionDTO> mapToDto(List<EventRegistrationQuestion> questionList);

    EventRegistrationQuestion mapToEntity(EventRegistrationQuestionDTO questionDTO);

    List<EventRegistrationQuestion> mapToEntity(List<EventRegistrationQuestionDTO> questionDTOList);


}
