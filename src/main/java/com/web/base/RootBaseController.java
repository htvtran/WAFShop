package com.web.base;

import java.lang.reflect.ParameterizedType;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.web.config.bind.Bind;
import com.web.config.bind.Bind.Ctrl;
import com.web.config.bind.ViewBindingBuilder;
import com.web.config.bind.ViewBindingRestBuilder;


public abstract class RootBaseController<T> implements ViewBindingBuilder {

	protected static final String PAGE_TITLE_VAR = "title";
	
	
	protected String pageView;
	protected String pageTitle;
	
	@Autowired
	Bind binder;
//
	private Ctrl vt;

	protected Class<T> type;
	
	
	@PostConstruct
	public void initView() {
		vt = binder.retreiveSpecificConfiguration(getGenericTypeClass());
		try {
			pageView = vt.getView();
			pageTitle = vt.getTitle();
		}catch(NullPointerException ex) {
			throw new RuntimeException(
			String.format(Bind.error,  Bind.getFormattedClassName(getGenericTypeClass())));
		}
		
		
	}
	

	@Override
	public String getViewName() {
		System.out.println("view " + pageView);
		return pageView;
	}

	
	public Ctrl getCtrlObjOf(String classname) {
		vt = binder.retreiveSpecificConfiguration(classname);
		if(vt == null) throw new NullPointerException();
		
		return vt;
	
	}
	
	// title in global.properties
	@Override
	public String getTitle() {
		System.out.println("title " + vt.getTitle());
		return pageTitle;
	}
	
	
	protected abstract  Class<T> getGenericTypeClass();

	
	public Ctrl getCtrlOf(String name) {
		return binder.retreiveSpecificConfiguration(name);
	}

}
