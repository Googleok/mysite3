package com.cafe24.mysite.validator.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ValidLogin {
	String message() default "Invalid Login";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
