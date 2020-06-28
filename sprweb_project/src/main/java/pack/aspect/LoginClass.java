package pack.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class LoginClass {
	public boolean loginCheck(HttpServletRequest request , HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("passwd") == null) {
			response.sendRedirect("admin_login"); 	//세션 name이 없으면 로그인으로 이동
			return true;
		}else {
			return false;
		}
	}
}
