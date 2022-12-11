package com.web.base;

import java.lang.reflect.ParameterizedType;

import org.springframework.web.bind.annotation.RestController;

import com.web.config.bind.ViewBindingRestBuilder;
import com.web.user.AccountController;


public abstract class BaseRestController<T> extends RootBaseController<T> implements ViewBindingRestBuilder{

	public BaseRestController() {}
	
	public void setType(Class<T> c) {
		type = c;
	}
	
	@Override
	protected Class<T> getGenericTypeClass() {
	    try {
	        String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
	        Class<?> clazz = Class.forName(className);
	        return (Class<T>) clazz;
	    } catch (Exception e) {
	        throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
	    }

	}
	

	


}
