package kr.co.study.api.common.engine.helper.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

/**
 * @since       2018.10.03
 * @author      lucas
 * @description object helper
 **********************************************************************************************************************/
@Component
public class ObjectHelper {

	@Autowired
	private ObjectHelper(ObjectMapper objectMapper) {
		ObjectHelper.objectMapper = objectMapper;
	}

	@SneakyThrows
	public static String toJson(Object o1){
		return objectMapper.writeValueAsString(o1);
	}

	@SneakyThrows
	public static <T> T toInstance(Class<T> clazz, String json){
		return objectMapper.readValue(json, clazz);
	}

	@SneakyThrows
	public static <T> T toInstance(Class<T> clazz, ResultActions resultActions){
		return toInstance(clazz, resultActions.andReturn().getResponse().getContentAsString());
	}

	@SneakyThrows
	public static Map toMap(Object o1){
		return objectMapper.convertValue(o1, Map.class);
	}

	public static <T> MultiValueMap<String, String> newMultiValueMap(T object) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap();
		parameters.setAll(objectMapper.convertValue(object, new TypeReference<Map<String, String>>() {}));
		return parameters;
	}

	@SneakyThrows
	public static <T> T newInstance(Class<T> clazz) {
		T t = clazz.newInstance();
		for(Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
				 if(Long.class          == field.getType()) { field.set(t, NumberUtils.LONG_ZERO);    }
			else if(Integer.class       == field.getType()) { field.set(t, NumberUtils.INTEGER_ZERO); }
			else if(BigDecimal.class    == field.getType()) { field.set(t, BigDecimal.ZERO);          }
			else if(Boolean.class       == field.getType()) { field.set(t, Boolean.TRUE);             }
			else if(String.class        == field.getType()) { field.set(t, field.getName());          }
			else if(LocalTime.class     == field.getType()) { field.set(t, LocalTime.now());          }
			else if(LocalDate.class     == field.getType()) { field.set(t, LocalDate.now());          }
			else if(LocalDateTime.class == field.getType()) { field.set(t, LocalDateTime.now());      }
			else if(field.getType().isEnum())               { field.set(t, field.getType().getEnumConstants()[NumberUtils.INTEGER_ZERO]); }
		}
		return t;
	}


	private static ObjectMapper objectMapper = null;
}
