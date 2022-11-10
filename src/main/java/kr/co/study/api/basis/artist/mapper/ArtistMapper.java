package kr.co.study.api.basis.artist.mapper;

import kr.co.study.api.basis.artist.entity.Artist;
import kr.co.study.api.basis.artist.form.ArtistForm.Request;
import kr.co.study.api.basis.artist.form.ArtistForm.Response;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @since       2022.10.31
 * @author      aslan
 * @description artist mapper
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArtistMapper {

    ArtistMapper mapper = Mappers.getMapper(ArtistMapper.class);

    Response.FindOne toFindOne(Artist entity);
    Response.FindAll toFindAll(Artist entity);

    Artist toArtist(Integer id);
    Artist toArtist(Request.Add form);
    Artist toArtist(Integer id, Request.Modify form);
}
