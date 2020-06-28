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

import pack.model.MemberDto;
import pack.model.MemberInter;
import pack.model.OrderDto;

@Controller
public class AdminMemberController {
	@Autowired
	@Qualifier("memberImpl")
	private MemberInter inter;

	@RequestMapping("admin_memberlist")
	public ModelAndView abc(HttpServletRequest request , HttpServletResponse response) {	// aop할때는 써줘야한다
		ModelAndView view = new ModelAndView();
		List<MemberDto> list = inter.memberList();
		view.setViewName("admin_memberlist");
		view.addObject("list" , list);
		return view;  
	} 
	
	
	@RequestMapping(value ="member_info", method = RequestMethod.GET)
	public ModelAndView memInfo(@RequestParam("member_id") String member_id) {
		ModelAndView orderlist = new ModelAndView();
		List<OrderDto> list = inter.myOrderList2(member_id);
		orderlist.setViewName("meminfo");
		orderlist.addObject("list", list);
		orderlist.addObject("count",list.size());
		return orderlist;
	}
}