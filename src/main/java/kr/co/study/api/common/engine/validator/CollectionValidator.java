package kr.co.study.api.common.engine.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.Validation;
import java.util.Collection;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description collection validator
 **********************************************************************************************************************/
@Component
public class CollectionValidator implements Validator {

	public CollectionValidator() {
		this.validator = new SpringValidatorAdapter( Validation.buildDefaultValidatorFactory().getValidator() );
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Collection.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Collection collection = (Collection) target;
		for (Object object : collection) {
			validator.validate(object, errors);
		}
	}
	
	
	private SpringValidatorAdapter validator;
}