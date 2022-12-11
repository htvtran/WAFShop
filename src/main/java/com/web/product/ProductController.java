package com.web.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.base.BaseRestController;

@RestController
@RequestMapping("product")
public class ProductController extends BaseRestController<ProductController> {
    

    @Autowired
    ProductDAO pDao;

    @GetMapping({""})
    public ModelAndView view(){
        ModelAndView mv =  getModalAndView();
        return mv;
    }

    @GetMapping("/detail")
    public ModelAndView getDetail(@RequestParam("id") Optional<Integer> id) {

        ModelAndView mv =  getModalAndView();
        Integer pid = id.orElse(null);
        Product p = pDao.getById(pid);
        mv.addObject("p", p);

        return mv;
    }


  


}
