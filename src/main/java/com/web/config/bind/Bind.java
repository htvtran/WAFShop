package com.web.config.bind;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/global.properties")
@ConfigurationProperties(prefix = "bind")
public class Bind {
	

//	need to declare in configure file
	
	public static String error = "Can't find bind.ctrl.%s.???(view and title) in global.properties make sure to declare them to extends BaseController";
	private Map<String, Ctrl> ctrl = new HashMap<>();

	
	

	public static String getError() {
		return error;
	}


	public static void setError(String error) {
		Bind.error = error;
	}




	public Ctrl retreiveSpecificConfiguration(Class<?> c) {
		String className = getFormattedClassName(c);
		// System.out.println("cn " + className);
		
		return retreiveSpecificConfiguration(className);
	}

	public Ctrl retreiveSpecificConfiguration(String name) {
		System.out.println(getCtrl().keySet()); 
		return ctrl.get(name);
	}

	
	public static String getFormattedClassName(String name) {
		return name.replaceAll("(.+?)Controller", "$1").toLowerCase();
	}
	
	public static String getFormattedClassName(Class<?> c) {
		String name = c.getSimpleName();
		if (name.contains("Controller")) {	
			return getFormattedClassName(name);
		}
		return name;
	}

	public Map<String, Ctrl> getCtrl() {
		return ctrl;
	}

	public void setCtrl(Map<String, Ctrl> ctrl) {
		this.ctrl = ctrl;
	}

	public static class Ctrl {

		String view;
		String title;

		public String getKey() {
			return view;
		}

//		public ViewTitle getViewTitleObj() {
//			return map.get(key);
//		}

		public void setKey(String key) {
			this.view = key;
		}

		public String getView() {
			return view;
		}

		public void setView(String view) {
			this.view = view;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	}



}
