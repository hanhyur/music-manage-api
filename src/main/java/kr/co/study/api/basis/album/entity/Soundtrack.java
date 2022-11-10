package kr.co.study.api.basis.album.entity;

import kr.co.study.api.common.base.entity.Base;
import kr.co.study.api.common.engine.annotation.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description soundtrack
 **********************************************************************************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Soundtrack extends Base {

    @Description("음원 PK")
    private Integer id;

    @Description("트랙 순서")
    private Integer trackNo;

    @Description("곡명")
    private String title;

    @Description("재생시간")
    private String playTime;

    @Description("노출여부")
    private Boolean exposure;

    @Description("앨범")
    private Album album;

}
