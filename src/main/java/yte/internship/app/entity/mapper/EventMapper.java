package yte.internship.app.entity.mapper;

import org.mapstruct.Mapper;
import yte.internship.app.entity.dto.EventDTO;
import yte.internship.app.entity.Event;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface EventMapper {

    EventDTO mapToDto(Event event);

    List<EventDTO> mapToDto(List<Event> eventList);

    Event mapToEntity(EventDTO eventDTO);

    List<Event> mapToEntity(List<EventDTO> eventDTOList);



}
