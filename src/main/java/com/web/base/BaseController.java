package com.web.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import com.web.config.bind.Bind;
import com.web.config.bind.ViewBindingBuilder;
import com.web.config.bind.Bind.Ctrl;


public class BaseController<T> extends RootBaseController implements ViewBindingBuilder{

	public String getView() {
		return getView();
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
