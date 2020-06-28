package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pack.model.DishDto;
import pack.model.MemberBean;
import pack.model.MemberDto;
import pack.model.MemberInter;
import pack.model.OrderDto;
import pack.model.ReviewDto;

@Controller
public class MyInfoController {

	@Autowired
	private MemberInter inter;
	
	//마이페이지 클릭 시 처음 화면으로 이동(주문내역)
	@RequestMapping(value ="myinfo", method = RequestMethod.GET)
	public ModelAndView myInfo(HttpSession session) {
		String login_id = (String)session.getAttribute("login_id");
		ModelAndView orderlist = new ModelAndView();
		List<OrderDto> list = inter.myOrderList(login_id);
		orderlist.addObject("list", list);
		orderlist.addObject("count",list.size());
		return orderlist;
	}
	
	//개인정보 클릭 시 이동
	@RequestMapping(value="myinfoupdate", method = RequestMethod.GET)
	public String myinfoupdatelink() {
		return "myinfoupdate";
	}
	
	//개인정보 화면에서 로그인 한 아이디와 비밀번호 일치 여부 확인 후 일치 시 데이터 출력
	@ResponseBody
	@RequestMapping(value="myinfoupdate", method = RequestMethod.POST)
	public Map<String, String> myinfoupdate(
			@RequestParam("myinfoupdate_id") String myinfoupdate_id,
			@RequestParam("myinfoupdate_passwd") String myinfoupdate_passwd) {
		Map<String, String> myinfoupdateResult = null;
		myinfoupdateResult = new HashMap<String, String>();
		MemberDto dto = inter.getUpdateInfo(myinfoupdate_id);
		if(dto != null) {
			String myinfoupdateResultId = dto.getMember_id();
			String myinfoupdateResultPasswd = dto.getMember_passwd();
			if(myinfoupdateResultId.equals(myinfoupdate_id) && myinfoupdateResultPasswd.equals(myinfoupdate_passwd)) {
				myinfoupdateResult.put("myinfoupdateResult","success");
				myinfoupdateResult.put("myinfoupdate_id", dto.getMember_id());
				myinfoupdateResult.put("myinfoupdate_name", dto.getMember_name());
				myinfoupdateResult.put("myinfoupdate_address", dto.getMember_address());
				myinfoupdateResult.put("myinfoupdate_address_detail", dto.getMember_address_detail());
				myinfoupdateResult.put("myinfoupdate_sdate", dto.getMember_sdate());
				return myinfoupdateResult;
			}
		}
		myinfoupdateResult.put("myinfoupdateResult","false");
		return myinfoupdateResult;
	}
	
	//개인정보에서 비밀번호 미변경 시 업데이트
	@RequestMapping("update1")
	public String updateinfo1(
			@RequestParam("my_id") String my_id,
			@RequestParam("new_name") String new_name,
			@RequestParam("new_address")String new_address,
			@RequestParam("new_address_detail")String new_address_detail) {
		MemberBean bean = new MemberBean();
		bean.setMember_id(my_id);
		bean.setMember_name(new_name);
		bean.setMember_address(new_address);
		bean.setMember_address_detail(new_address_detail);
		
		inter.updateMember(bean);
		return "myinfoupdate";
	}
	
	//개인정보에서 비밀번호 변경 시 업데이트
	@RequestMapping("update2")
	public String updateinfo2(
			@RequestParam("my_id") String my_id,
			@RequestParam("new_passwd") String new_passwd,
			@RequestParam("new_name") String new_name,
			@RequestParam("new_address")String new_address,
			@RequestParam("new_address_detail")String new_address_detail) {
		MemberBean bean = new MemberBean();
		bean.setMember_id(my_id);
		bean.setMember_passwd(new_passwd);
		bean.setMember_name(new_name);
		bean.setMember_address(new_address);
		bean.setMember_address_detail(new_address_detail);
		
		inter.updateMember2(bean);
		return "myinfoupdate";
	}
	
	//회원탈퇴
	@RequestMapping("deleteMember")
	public String deleteMember(HttpSession session) {
		String login_id = (String)session.getAttribute("login_id");
		inter.deleteMember(login_id);
		return "redirect:logout";
	}
	
	//주문내역 상세보기
	@ResponseBody
	@RequestMapping("myOrderDetail")
	public Map<String, Object> myOrderDetail(@RequestParam("order_no") String order_no){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;
		List<DishDto> dto = inter.myOrderDetail(order_no);
		
		for(DishDto s:dto) {
			data = new HashMap<String, String>();
			data.put("menu_no", s.getMenu_no());
			data.put("menu_name", s.getMenu_name());
			data.put("menu_price", s.getMenu_price());
			data.put("menu_count", s.getMenu_count());
			data.put("menu_category", s.getMenu_category());
			dataList.add(data);
		}
		
		Map<String, Object> selectOrderResult = new HashMap<String, Object>();
		selectOrderResult.put("list", dataList);
		return selectOrderResult;
	}
	
	//내 리뷰 목록
		@RequestMapping("myreview")
		public ModelAndView myreview(HttpSession session) {
			String login_id = (String)session.getAttribute("login_id");
			ModelAndView reviewlist = new ModelAndView();
			List<ReviewDto> list = inter.myreview(login_id);
			reviewlist.addObject("list", list);
			reviewlist.addObject("count",list.size());
			return reviewlist;
		}
	
	//내 리뷰 삭제
		@ResponseBody
		@RequestMapping("myreviewdelete")
		public Map<String, String> myreviewdelete(@RequestParam("review_no") String review_no) {
			Map<String, String> myreviewdeleteResult = new HashMap<String, String>();
			if(inter.myreviewdelete(review_no) > 0 ) {
				myreviewdeleteResult.put("myreviewdeleteResult", "success");
				return myreviewdeleteResult;
			}
			else {
				myreviewdeleteResult.put("myreviewdeleteResult", "false");
				return myreviewdeleteResult;
			}
		}
}
