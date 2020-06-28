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

import pack.model.ReviewDaoInter;
import pack.model.ReviewDto;

@Controller
public class AdminReviewController {
	@Autowired
	@Qualifier("reviewDaoImpl")
	private ReviewDaoInter inter;

	@RequestMapping("admin_reviewlist")
	public ModelAndView abc(HttpServletRequest request , HttpServletResponse response) {	// aop할때는 써줘야한다
		ModelAndView view = new ModelAndView();
		List<ReviewDto> list = inter.selectAll();
		view.setViewName("admin_reviewlist");
		view.addObject("list" , list);
		return view;  
	} 
	
	@RequestMapping(value = "review_del" , method = RequestMethod.GET)
	public String del(@RequestParam("review_no") String review_no) {
		if(inter.deleteReview(review_no)) {
			return "redirect:/admin_reviewlist";	//수정 후 리스트로 돌아감
		}else
			return "error";	//error.jsp를 만나게됨
	}
	
}