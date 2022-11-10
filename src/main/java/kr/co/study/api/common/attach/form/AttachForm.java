package kr.co.study.api.common.attach.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @since       2020.02.20
 * @author      lucas
 * @description attach form
 **********************************************************************************************************************/
public class AttachForm {

	public static class Request {

		@Getter
		@Setter
		@Builder(toBuilder=true)
		@ToString
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Remove {

			@ApiModelProperty(value="경로", required=true)
			@NotBlank
			private String path;

			@ApiModelProperty(value="이름", required=true)
			@NotBlank
			private String name;
		}
	}

	public static class Response {

		@Getter
		@Setter
		@Builder(toBuilder=true)
		@ToString
		@NoArgsConstructor
		@AllArgsConstructor
		public static class FindOne {

			@ApiModelProperty(value="경로")
			private String path;

			@ApiModelProperty(value="이름")
			private String name;
		}
	}
}