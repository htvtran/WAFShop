package com.web.product;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.product.detail.Color;
import com.web.product.detail.Size;

@Controller
public class ColorController {

    @Autowired
	SizeDAO sizeDAO;
	@Autowired
	ColorDAO colorDAO;
    @Autowired
	HttpSession session;
    
    @RequestMapping("/admin/quanlySizeColor")
	public ModelAndView getViewDetail(Model model,
    Size size,Color color){

		session.setAttribute("size", sizeDAO.findAll());
		session.setAttribute("color", colorDAO.findAll());
        model.addAttribute("size", size);
        model.addAttribute("color", color);
		System.out.println(sizeDAO.findAll());
		return new ModelAndView("/admin/quanlySizeColor"); 

	}

    @RequestMapping("/admin/quanlySizeColor/{sizeOrColor}")
	public ModelAndView newOrEditSize(Model model,
    Size size,Color color,@PathVariable("sizeOrColor") String op){

	
  
        if(op.equals("size")){
            sizeDAO.save(size);
        }
	

        session.setAttribute("size", sizeDAO.findAll());
		session.setAttribute("color", colorDAO.findAll());

		return new ModelAndView("/admin/quanlySizeColor"); 

	}
	
}
