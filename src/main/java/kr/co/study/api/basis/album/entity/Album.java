package kr.co.study.api.basis.album.entity;

import kr.co.study.api.basis.artist.entity.Artist;
import kr.co.study.api.common.base.entity.Base;
import kr.co.study.api.common.engine.annotation.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album
 **********************************************************************************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Album extends Base {

    @Description("앨범 PK")
    private Integer id;

    @Description("앨범 제목")
    private String title;

    @Description("발매일")
    private LocalDate releaseDate;

    @Description("장르")
    private String genre;

    @Description("앨범 소개")
    private String description;

    @Description("아티스트")
    private Artist artist;

    @Description("음원 목록")
    private List<Soundtrack> soundtracks;

}
