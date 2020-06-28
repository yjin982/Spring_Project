package pack.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.CartDao;
import pack.model.CartDto;
import pack.model.DishDaoInter;
import pack.model.DishDto;

@Controller
public class HomeController {
	@Autowired
	@Qualifier("dishDaoImpl")
	private DishDaoInter daoInter;
	
	@Autowired
	private CartDao dao;
	
	@RequestMapping("aa")
	public String home(HttpSession session, Locale locale, Model model) { //전체 메뉴 가져오기
		List<DishDto> list = daoInter.getDataAll();
		model.addAttribute("item", list);
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			model.addAttribute("carts", list2);			
		}
		return "home";
	}
}
