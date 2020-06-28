package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.DishDto;
import pack.model.ReviewBean;
import pack.model.ReviewDaoInter;
import pack.model.ReviewDto;

@Controller
public class ReviewController {

	@Autowired
	@Qualifier("reviewDaoImpl")
	private ReviewDaoInter reviewDaoInter;

	// 리뷰 목록 보기
	@RequestMapping("review")
	@ResponseBody
	public Map<String, Object> revFunc(@RequestParam("menu_no") String no) {
		List<Map<String, String>> reviewList = new ArrayList<Map<String, String>>();
		Map<String, String> data = null;
		System.out.println("-----------------" + no);

		List<ReviewDto> revList = reviewDaoInter.getdataPart(no);
		// System.out.println("-------" + revList);
		for (ReviewDto r : revList) {
			data = new HashMap<String, String>();

			data.put("review_no", r.getReview_no());
			data.put("review_id", r.getReview_id());
			data.put("review_comment", r.getReview_comment());
			data.put("review_menu_num", r.getReview_menu_num());
			data.put("review_sdate", r.getReview_sdate());
			reviewList.add(data);
			//System.out.println(r.getReview_no() + r.getReview_id() + r.getReview_comment() + r.getReview_menu_num() + r.getReview_sdate());
		}

		Map<String, Object> reviews = new HashMap<String, Object>();
		reviews.put("datas", reviewList);
		return reviews;
	}

	//리뷰 작성 칸 내용 보이기
	@ResponseBody
	@RequestMapping(value = "rev_frm", method = RequestMethod.GET)
	public Map<String, Object> revfrm(@RequestParam("menu_no") String menu_no) {
		Map<String, Object> result = new HashMap<String, Object>();
		DishDto listDto = reviewDaoInter.selectPart(menu_no);
		result.put("menu_no", listDto.getMenu_no());
		result.put("menu_name", listDto.getMenu_name());
		result.put("menu_category", listDto.getMenu_category());
		
		return result;
		
	}
	
	 // 리뷰추가	  
	@RequestMapping(value = "rev_frm", method = RequestMethod.POST)	  
	@ResponseBody	  
	public Map<String, String> insert(
			  @RequestParam("review_menu_comment") String review_comment,
			  @RequestParam("review_menu_category") String menu_category,
			  @RequestParam("review_menu_num") String review_menu_num,
			  @RequestParam("review_id") String review_id,
			  @RequestParam("review_menu_name") String menu_name) {
			System.out.println(review_comment);
		  Map<String, String> reviewResult = new HashMap<String, String>(); 
		  	ReviewBean bean = new ReviewBean(); 		  		
		  		bean.setReview_comment(review_comment);
		  		bean.setReview_menu_num(review_menu_num);
		  		bean.setReview_id(review_id);
		  		bean.setMenu_name(menu_name);
		  		
		  		if (reviewDaoInter.insert(bean) > 0) {
		  			reviewResult.put("reviewResult", "success"); 
		  			return reviewResult;
		  		}else {		  		
		  		reviewResult.put("reviewResult", "false");
		  		return reviewResult;
		  		}	  		
				  	
	  }
	
		//리뷰 수정 칸 내용 보이기
		@RequestMapping(value = "up_frm", method = RequestMethod.GET)
		@ResponseBody
		public Map<String, Object> upfrm(@RequestParam("review_no") String review_no) {
			Map<String, Object> result = new HashMap<String, Object>();
			ReviewDto reDto = reviewDaoInter.selectPartup(review_no);
			System.out.println(reDto.getReview_menu_num());
			result.put("menu_no", reDto.getReview_menu_num());
			result.put("menu_name", reDto.getMenu_name());
			result.put("menu_category", reDto.getMenu_category());
			result.put("review_comment", reDto.getReview_comment());
			result.put("review_no", reDto.getReview_no());
			return result;
			
		}
		
		//리뷰 수정
		@RequestMapping(value = "rev_up", method = RequestMethod.POST)	  
		@ResponseBody	  
		public Map<String, String> upfrm(
				@RequestParam("review_no3") String review_no,
				  @RequestParam("review_comment3") String review_comment) {
				System.out.println(review_comment);
			  Map<String, String> reviewResult = new HashMap<String, String>(); 
			  	ReviewBean bean = new ReviewBean(); 
			  		//bean.getReview_no(review_no);
			  		
			  		bean.setReview_no(review_no);
			  		bean.setReview_comment(review_comment);
			  		
			  		if (reviewDaoInter.update(bean) > 0) {
			  			reviewResult.put("reviewResult", "success"); 
			  			return reviewResult;
			  		}else {		  		
			  		reviewResult.put("reviewResult", "false");
			  		return reviewResult;
			  		}	  		
					  	
		  }
		
		//전체 목록 보기
		@RequestMapping("reviewTot")
		@ResponseBody
		public Map<String, Object> revTOtFunc(){
			List<Map<String, String>> dataTotList = new ArrayList<Map<String,String>>();
			Map<String, String> data = null;
			
			List<ReviewDto> reList = reviewDaoInter.selectAll();
			
			for(ReviewDto s:reList) { 
				data = new HashMap<String, String>();				
			
				data.put("review_id", s.getReview_id());
				data.put("menu_name", s.getMenu_name());
				data.put("menu_category", s.getMenu_category());
				data.put("review_comment", s.getReview_comment());
				data.put("review_sdate", s.getReview_sdate());
				
				dataTotList.add(data);				
			}
			
			Map<String, Object> reviewss = new HashMap<String, Object>();
			reviewss.put("datas", dataTotList);
			return reviewss;
		}
		
}
