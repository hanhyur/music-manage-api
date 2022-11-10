package kr.co.study.api.common.base.entity;

import lombok.*;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @since       2020.02.12
 * @author      preah
 * @description paging
 **********************************************************************************************************************/
@Setter
@Getter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> {

	private Integer number;
	private Integer size;
	private Integer total;
	private List<T> contents;

	public <U> Paging<U> map(Function<? super T, ? extends U> converter) {
		return new Paging(number, size, total, ObjectUtils.isEmpty(contents) ? Collections.emptyList() : contents.stream().map(converter::apply).collect(Collectors.toList()));
	}
}