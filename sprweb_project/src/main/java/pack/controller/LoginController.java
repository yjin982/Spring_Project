package pack.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.MemberDto;
import pack.model.MemberInter;

@Controller
public class LoginController {
	@Autowired
	private MemberInter inter;
	
	//로그인
	@ResponseBody
	@RequestMapping(value = "login", method =  RequestMethod.POST)
	public Map<String, String> goSubmit(HttpSession session,
	@RequestParam("login_id") String login_id,
	@RequestParam("login_passwd")String login_passwd){
		Map<String, String> loginResult = null;
		loginResult = new HashMap<String, String>();
		MemberDto dto = inter.getLoginInfo(login_id);
		if(dto != null) {
			String loginResultId = dto.getMember_id();
			String loginResultPasswd = dto.getMember_passwd();
			if(loginResultId.equals(login_id) && loginResultPasswd.equals(login_passwd)) {
				session.setAttribute("login_id", login_id);
				loginResult.put("loginResult","success");
				return loginResult;
			}
		}
		loginResult.put("loginResult","false");
		return loginResult;
		
	}

	@ResponseBody
	@RequestMapping("loginCheck")
	public Map<String, String> loginCheck(HttpSession session) {
		Map<String, String> loginCheck = null;
		loginCheck = new HashMap<String, String>();
		
		if(session.getAttribute("login_id") == null) {
			loginCheck.put("loginCheckResult", "false");
			
		}else {
			loginCheck.put("loginCheckResult", "success");
		}
		return loginCheck;
	}
	
	
	//로그아웃
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String goLogout(HttpSession session) {
		session.invalidate();
		return "redirect:aa";
	}
}
