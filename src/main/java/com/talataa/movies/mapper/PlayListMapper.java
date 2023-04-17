package com.talataa.movies.mapper;


import com.talataa.movies.dto.PlayListDTO;
import com.talataa.movies.entities.PlayList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface PlayListMapper {

    @Mapping(target = "createdBy", source = "src.created_by")
    @Mapping(target = "favoriteCount", source = "src.favorite_count")
    @Mapping(target = "totalMovies", source = "src.item_count")
    @Mapping(target = "page", source = "page")
    @Mapping(target = "movies", source = "src.items", qualifiedByName = "movieMapper")
    @Mapping(target = "totalPages", source = "totalPages")
    @Mapping(target = "posterUrl", expression = "java((src.getPoster_path() != null) ? imgUrl.concat(src.getPoster_path()) : null)")
    public PlayListDTO playListAsPlayListDTO(PlayList src, Integer page, Integer totalPages, String imgUrl);

}
