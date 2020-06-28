package pack.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pack.model.CartBean;
import pack.model.CartDao;
import pack.model.CartDto;
import pack.model.DishDaoInter;

@Controller
@ComponentScan("pack.model")
public class ListController {
	@Autowired
	@Qualifier("dishDaoImpl")
	private DishDaoInter daoInter;
	
	@Autowired
	private CartDao dao;
	
	@RequestMapping("/")
	public ModelAndView list() {
		return new ModelAndView("home", "item", daoInter.getDataAll());
	}
	
	////////////////↓ 항목별 조회
	@RequestMapping(value = "category1" , method = RequestMethod.GET)
	public ModelAndView dishFunc1(HttpSession session, @RequestParam("check") String menu_category) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		mav.addObject("item", daoInter.getSoup(menu_category));
		mav.addObject("cate", "국");
		return mav;
	}
	
	@RequestMapping(value = "category2" , method = RequestMethod.GET)
	public ModelAndView dishFunc2(HttpSession session, @RequestParam("check") String menu_category) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("home");
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		mav.addObject("item", daoInter.getSoup(menu_category));
		mav.addObject("cate", "반찬");
		return mav;
	}
	
	@RequestMapping(value = "category3" , method = RequestMethod.GET)
	public ModelAndView dishFunc3(HttpSession session, @RequestParam("check") String menu_category) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		mav.addObject("item", daoInter.getSoup(menu_category));
		mav.addObject("cate", "메인");
		return mav;
	}
	
	@RequestMapping(value = "category4" , method = RequestMethod.GET)
	public ModelAndView dishFunc4(HttpSession session, @RequestParam("check") String menu_category) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		mav.addObject("item", daoInter.getSoup(menu_category));
		mav.addObject("cate", "육류");
		return mav;
	}
	
	@RequestMapping(value = "category5" , method = RequestMethod.GET)
	public ModelAndView dishFunc5(HttpSession session, @RequestParam("check") String menu_category) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		mav.addObject("item", daoInter.getSoup(menu_category));
		mav.addObject("cate", "샐러드");
		return mav;
	}
	
	@RequestMapping(value = "category6" , method = RequestMethod.GET)
	public ModelAndView dishFunc6(HttpSession session, @RequestParam("check") String menu_category) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		mav.addObject("item", daoInter.getSoup(menu_category));
		mav.addObject("cate", "김치");
		return mav;
	}
	
	
	////////////////↓ 정렬
	@RequestMapping(value = "sortReg", method = RequestMethod.GET)
	public ModelAndView sortReg(HttpSession session, @RequestParam("check") String menu_category, @RequestParam("sort") String sort) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("cate", menu_category);
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		if(menu_category.equals("")) {
			mav.addObject("item", daoInter.sortAllReg());
		}else {
			mav.addObject("item", daoInter.sortLately(menu_category));
		}
		return mav;			
	}
	
	@RequestMapping(value = "sortLow", method = RequestMethod.GET)
	public ModelAndView sortLow(HttpSession session, @RequestParam("check") String menu_category, @RequestParam("sort") String sort) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("cate", menu_category);

		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		if(menu_category.equals("")) {
			mav.addObject("item", daoInter.sortAllLow());
			return mav;		
		}else {
			mav.addObject("item", daoInter.sortLow(menu_category));
		}
		return mav;			
	}
	
	@RequestMapping(value = "sortHigh", method = RequestMethod.GET)
	public ModelAndView sortHigh(HttpSession session, @RequestParam("check") String menu_category, @RequestParam("sort") String sort) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("cate", menu_category);
		
		String login_id = (String)session.getAttribute("login_id");
		if(login_id != null) {//로그인 상태일때 카트 목록을 보이도록
			List<CartDto> list2 = dao.cartlist(login_id);
			mav.addObject("carts", list2);			
		}
		
		if(menu_category.equals("")) {
			mav.addObject("item", daoInter.sortAllHigh());
		}else {
			mav.addObject("item", daoInter.sortHigh(menu_category));
		}
		return mav;
	}
}
