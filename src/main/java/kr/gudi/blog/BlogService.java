package kr.gudi.blog;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

	@Autowired BlogDao bd;
	private String resultCode;
	private Map<String, Object> resultMap;
	
	public int signUp(Map<String, Object> paramMap) {
		if(bd.userCheck(paramMap) > 0) return 0;
		else		  				   return bd.signUp(paramMap);
	}
	
	public String login(HttpSession session, Map<String, Object> paramMap) throws Exception {
		resultMap = bd.login(paramMap);		
		if(resultMap == null) {
			resultCode = "0";
		} else {
			session.setAttribute("user", resultMap);
			resultCode = "1";
		}		
		return resultCode;
	}
	
	public int userUpdate(HttpSession session, Map<String, Object> paramMap) {
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		paramMap.put("no", userMap.get("no"));
		return bd.userUpdate(paramMap);
	}

}
