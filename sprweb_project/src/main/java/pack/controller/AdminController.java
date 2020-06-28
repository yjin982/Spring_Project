package pack.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.AdminDto;
import pack.model.DishDaoInter;

@Controller
public class AdminController {
	@Autowired
	@Qualifier("dishDaoImpl")
	private DishDaoInter inter;
	
	@RequestMapping(value = "admin_login" , method = RequestMethod.GET)
	public String goLogin() {
		return "admin_login";
	}
	
	@RequestMapping(value = "admin_login" , method=RequestMethod.POST)
	public String goSubmit(HttpSession session,@RequestParam("id") String id , @RequestParam("passwd") String passwd) {
		AdminDto dto = inter.getLoginInfo(id);	//받은 사번으로 조회
		
		if(dto !=null) {
			String resultPasswd = dto.getAdmin_passwd();
			if(resultPasswd.equals(passwd)) {	//세션으로 받은이름과 db에서 가져온이름이 맞으면 
				session.setAttribute("passwd", passwd); 	
			}
		}
		
		return "redirect:/admin.jsp";
	}
	

	@RequestMapping(value = "admin_logout" , method = RequestMethod.GET)
	public String goLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin.jsp";
	}
}
