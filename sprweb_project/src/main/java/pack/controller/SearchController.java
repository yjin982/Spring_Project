package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.DishBean;
import pack.model.DishDaoImpl;
import pack.model.DishDto;

@Controller
public class SearchController {
	
	@Autowired
	private DishDaoImpl dishDaoImpl;
	
	@RequestMapping("searchfrm")
	public ModelAndView search(DishBean bean) {
		List<DishDto> list = dishDaoImpl.search(bean);
		return new ModelAndView("home", "item" , list);
	}
}
