package pack.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.DishBean;
import pack.model.DishDaoInter;
import pack.model.DishDto;

@Controller
public class MenuController {
	@Autowired
	@Qualifier("dishDaoImpl")
	private DishDaoInter inter;

	@RequestMapping("admin_menulist")
	public ModelAndView abc(HttpServletRequest request , HttpServletResponse response) {	// aop할때는 써줘야한다
		ModelAndView view = new ModelAndView();
		List<DishDto> list = inter.menuList();
		view.setViewName("admin_menulist");
		view.addObject("list" , list);
		return view;
	}
	
	@RequestMapping(value ="menu_insert" , method=RequestMethod.GET)
	public String insert() {
		return "insform";
	}

	
	@RequestMapping(value = "menu_insert" , method=RequestMethod.POST)
	public String inssubmit(DishBean bean) {
		boolean b = inter.insertData(bean);
		System.out.println(bean.getMenu_name());
		System.out.println(bean.getMenu_no());
		if(b) {
			return "redirect:/admin_menulist";	//추가 완료후 리스트로 돌아감
		}else
			return "error";	//error.jsp를 만나게됨
	}
	
	@RequestMapping(value = "menu_update" , method = RequestMethod.GET)
	public ModelAndView up(@RequestParam("menu_no") String menu_no) {
		DishDto dto = inter.selectPart(menu_no);
		return new ModelAndView("upform","dto",dto);
	}
	
	@RequestMapping(value = "menu_update" , method=RequestMethod.POST)
	public String upsubmit(DishBean bean) {
		boolean b = inter.updateData(bean);
		if(b) { 
			return "redirect:/admin_menulist";	//수정 후 리스트로 돌아감
		}else
			return "error";	//error.jsp를 만나게됨
	}

	@RequestMapping("menu_delete")
	public String del(@RequestParam("menu_no") String menu_no) {
		if(inter.deleteData(menu_no)) {
			return "redirect:/admin_menulist";	//수정 후 리스트로 돌아감
		}else
			return "error";	//error.jsp를 만나게됨
	}


}