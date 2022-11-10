package kr.co.study.api.common.attach.entity;

import kr.co.study.api.common.engine.annotation.entity.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since       2020.02.20
 * @author      lucas
 * @description attach
 **********************************************************************************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Attach {

    @Description("경로")
    private String path;

    @Description("이름")
    private String name;

    @Description("크기")
    private Long size;
}