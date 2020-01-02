package kr.gudi.blog;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	public static List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();

	public boolean signUp(HttpSession session, HttpServletRequest req, HttpServletResponse res) {
		try {
			req.setCharacterEncoding("UTF-8");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Enumeration<String> enume = req.getParameterNames();
			while (enume.hasMoreElements()) {
			       String paramName = enume.nextElement();
			       String paramValue = req.getParameter(paramName);
			       paramMap.put(paramName, paramValue);
			}
			BlogService.userList.add(paramMap);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void login(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("UTF-8");		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		for(int i = 0; i < BlogService.userList.size(); i++) {
			Map<String, Object> userMap = BlogService.userList.get(i);
			Object userId = userMap.get("id");
			Object userPwd = userMap.get("pwd");
			Object UserName = userMap.get("name");
			
			if(userId.equals(id)) {
				if(userPwd.equals(pwd)) {
					res.getWriter().print("1");
					session.setAttribute("id", id);
					session.setAttribute("name", UserName);
					return;
				}
			}
			
		}
		res.getWriter().print("2");		
	}
	
	public boolean loginCheck(HttpSession session) {
		try {
			Object id = session.getAttribute("id");
			if(id == null) {
				System.out.println("id 없음");
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
