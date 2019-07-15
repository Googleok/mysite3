package com.cafe24.mysite.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cafe24.mysite.validator.constraints.ValidLogin;

public class LoginValidator implements ConstraintValidator<ValidLogin, String>{

	@Override
	public void initialize(ValidLogin constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return false;
	}

}
