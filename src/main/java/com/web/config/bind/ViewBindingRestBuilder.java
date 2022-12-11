package com.web.config.bind;

import org.springframework.web.servlet.ModelAndView;

import com.web.config.bind.Bind.Ctrl;

public interface ViewBindingRestBuilder extends ViewBindingBuilder{

	public String getViewName();
	
	public String getTitle();
	
	default ModelAndView getModalAndView() {
		ModelAndView mv = getModalAndView(getViewName());
		String title =  getTitle()==null? "Untitled" : getTitle();
		mv.addObject("title",title);
		return mv;
	};
	
	default ModelAndView getModalAndView(String viewName) {
		System.out.println("request viewName: " + viewName);
		return new ModelAndView(viewName);
		
	}
	
	default ModelAndView getModalAndView(Ctrl ctrl) {
		String viewName = ctrl.getView();
		System.out.println("request viewName: " + viewName);
		return new ModelAndView(viewName);
		
	}

}
