package kr.co.study.api.basis.album.mapper;

import kr.co.study.api.basis.album.entity.Album;
import kr.co.study.api.basis.album.form.AlbumForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album mapper
 **********************************************************************************************************************/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlbumMapper {

    AlbumMapper mapper = Mappers.getMapper(AlbumMapper.class);

    AlbumForm.Response.FindOne toFindOne(Album entity);
    AlbumForm.Response.FindAll toFindAll(Album entity);

    Album toAlbum(Integer id);
    Album toAlbum(AlbumForm.Request.Add form);
    Album toAlbum(Integer id, AlbumForm.Request.Modify form);

}
