package yte.internship.app.entity.mapper;
import org.mapstruct.Mapper;
import yte.internship.app.entity.EventRegistrationQuestionUserAnswer;
import yte.internship.app.entity.dto.EventRegistrationQuestionUserAnswerDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventRegistrationQuestionUserAnswerMapper {

    EventRegistrationQuestionUserAnswer mapToDto(EventRegistrationQuestionUserAnswer answer);


    EventRegistrationQuestionUserAnswer mapToEntity(EventRegistrationQuestionUserAnswerDTO answerDTO);

    List<EventRegistrationQuestionUserAnswer> mapToEntity(List<EventRegistrationQuestionUserAnswerDTO> answerDTOList);
}
