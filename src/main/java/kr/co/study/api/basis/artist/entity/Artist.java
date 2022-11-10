package kr.co.study.api.basis.artist.entity;

import kr.co.study.api.common.base.entity.Base;
import kr.co.study.api.common.engine.annotation.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @since       2022.11.01
 * @author      aslan
 * @description artist
 **********************************************************************************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Artist extends Base {

    @Description("아티스트 PK")
    private Integer id;

    @Description("이름")
    private String name;

    @Description("생일")
    private LocalDate birth;

    @Description("소속사")
    private String agency;

    @Description("국적")
    private String nationality;

    @Description("소개")
    private String introduction;

}