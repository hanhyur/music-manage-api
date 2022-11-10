package kr.co.study.api.common.base.form;

import io.swagger.annotations.ApiModelProperty;
import kr.co.study.api.common.engine.constant.Constant;
import lombok.*;

/**
 * @since       2020.02.19
 * @author      lucas
 * @description pageable
 **********************************************************************************************************************/
@Setter
@Getter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Pageable {

	@ApiModelProperty(value="현재페이지번호")
	private Integer number = Constant.Integer.ZERO;

	@ApiModelProperty(value="페이지크기")
	private Integer size   = Constant.Integer.TEN;

	@ApiModelProperty(value="전체갯수")
	private Integer total  = Constant.Integer.ZERO;
}