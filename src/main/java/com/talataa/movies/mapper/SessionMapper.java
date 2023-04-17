package com.talataa.movies.mapper;

import com.talataa.movies.dto.SessionDTO;
import com.talataa.movies.entities.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(target = "sessionId", source = "session_id")
    public SessionDTO sessionAsSessionDTO(Session src);

}
