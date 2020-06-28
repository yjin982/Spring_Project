package pack.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.MemberBean;
import pack.model.MemberDto;
import pack.model.MemberInter;

@Controller
public class JoinController {
	@Autowired
	private MemberInter inter;
	
	//회원가입 값 넣기
	@ResponseBody
	@RequestMapping(value = "join", method =  RequestMethod.POST)
	public Map<String, String> join(
	@RequestParam("join_id") String join_id,
	@RequestParam("join_passwd")String join_passwd,
	@RequestParam("join_name") String join_name,
	@RequestParam("join_address") String join_address,
	@RequestParam("join_address_detail") String join_address_detail){
		Map<String, String> joinResult = new HashMap<String, String>(); 
		MemberBean bean = new MemberBean();
		bean.setMember_id(join_id);
		bean.setMember_passwd(join_passwd);
		bean.setMember_name(join_name);
		bean.setMember_address(join_address);
		bean.setMember_address_detail(join_address_detail);
		if(inter.joinMember(bean)>0) {
			joinResult.put("joinResult", "success");
			return joinResult;
		}
		joinResult.put("joinResult", "false");
		return joinResult;
		
	}
	
	//회원가입 아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "joinIdCheck", method =  RequestMethod.POST)
	public Map<String, String> joinIdCheck(@RequestParam("join_id") String join_id){
		Map<String, String> joinIdCheckResult = null;
		joinIdCheckResult = new HashMap<String, String>(); 
			if(inter.joinIdCheck(join_id)>0) {
				joinIdCheckResult.put("joinIdCheckResult", "false");
			}
			else {
				joinIdCheckResult.put("joinIdCheckResult", "success");
			}
			return joinIdCheckResult;
		
	}
	

}
