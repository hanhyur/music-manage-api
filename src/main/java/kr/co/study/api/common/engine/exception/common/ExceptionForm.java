package kr.co.study.api.common.engine.exception.common;

import lombok.*;

/**
 * @since       2020.20.24
 * @author      lucas
 * @description exception form
 **********************************************************************************************************************/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionForm {

	private ExceptionCode code;
	private String message;
}