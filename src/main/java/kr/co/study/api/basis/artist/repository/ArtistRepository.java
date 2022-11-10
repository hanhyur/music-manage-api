package kr.co.study.api.basis.artist.repository;

import kr.co.study.api.basis.artist.entity.Artist;
import kr.co.study.api.basis.artist.form.ArtistForm.Request;
import kr.co.study.api.common.base.entity.Paging;
import kr.co.study.api.common.base.form.Pageable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * @since       2022.10.31
 * @author      aslan
 * @description artist repository
 **********************************************************************************************************************/
@Mapper
public interface ArtistRepository {

    Paging<Artist> getPage(@Param("find") Request.Find find, @Param("page") Pageable page);

    Optional<Artist> get(@Param("artist") Artist artist);

    Integer add(@Param("artist") Artist artist);

    Integer modify(@Param("artist") Artist artist);

    void remove(@Param("artist") Artist artist);

}
