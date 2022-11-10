package kr.co.study.api.common.engine.helper.api;

import lombok.*;
import org.springframework.http.HttpMethod;

/**
 * @since       2020.03.09
 * @author      lucas
 * @description api
 **********************************************************************************************************************/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Api {

	private Class clazz;
	private HttpMethod method;
	private String uri;
	private String description;
}