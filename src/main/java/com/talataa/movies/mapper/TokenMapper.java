package com.talataa.movies.mapper;

import com.talataa.movies.dto.SessionDTO;
import com.talataa.movies.dto.TokenDTO;
import com.talataa.movies.entities.Session;
import com.talataa.movies.entities.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(target = "token", source = "request_token")
    @Mapping(target = "expiresAt", source = "expires_at", dateFormat = "yyyy-MM-dd HH:mm:ss z")
    @Mapping(target="tokenApprovalUrl", expression = "java(\"https://www.themoviedb.org/authenticate/\".concat(src.getRequest_token()))")
    public TokenDTO tokenAsTokenDTO(Token src);

}
