package kr.co.study.api.common.engine.helper.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @since       2020.02.19
 * @author      lucas
 * @description snake case json helper
 **********************************************************************************************************************/
@Component
public class SnakeCaseJsonHelper {

	private static ObjectMapper objectMapper = null;

	@Autowired
	private SnakeCaseJsonHelper(ObjectMapper objectMapper) {
		SnakeCaseJsonHelper.objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@SneakyThrows
	public static String toJson(Object o1){
		return objectMapper.writeValueAsString(o1);
	}
}
