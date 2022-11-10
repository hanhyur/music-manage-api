package kr.co.study.api.basis.album.repository;

import kr.co.study.api.basis.album.entity.Album;
import kr.co.study.api.basis.album.form.AlbumForm.Request;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.base.form.Pageable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album repository
 **********************************************************************************************************************/
@Mapper
public interface AlbumRepository {

    Paging<Album> getPage(@Param("find") Request.Find find, @Param("page") Pageable page);

    Album get(@Param("album") Album album);

    Integer add(@Param("album") Album album);

    Integer modify(@Param("album") Album album);

    void remove(@Param("album") Album album);

}
