package pack.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
public class MyAdvice {
	@Autowired
	private LoginClass loginClass;

	@Around("execution(* abc"
			+ ""
			+ "*(..))")
	public Object aopProcess(ProceedingJoinPoint joinPoint) throws Throwable{
		HttpServletResponse response = null;
		HttpServletRequest request = null;

		for(Object obj:joinPoint.getArgs()) {
			if(obj instanceof HttpServletResponse) {
				response = (HttpServletResponse)obj;	
			}
			if(obj instanceof HttpServletRequest) {
				request = (HttpServletRequest)obj;	
			}

		}
		if(loginClass.loginCheck(request, response)) {
			return null;
		}

		Object object = joinPoint.proceed();
		return object;
	}
}
